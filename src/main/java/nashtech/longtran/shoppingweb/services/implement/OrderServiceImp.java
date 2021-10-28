package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.entity.Order;
import nashtech.longtran.shoppingweb.entity.OrderDetail;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.enums.EOrderStatus;
import nashtech.longtran.shoppingweb.exception.OrderIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.ProductIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.OrderEditStatusRequest;
import nashtech.longtran.shoppingweb.payload.request.OrderRequest;
import nashtech.longtran.shoppingweb.repository.OrderDetailRepository;
import nashtech.longtran.shoppingweb.repository.OrdersRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.repository.UserRepository;
import nashtech.longtran.shoppingweb.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImp implements IOrderService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Order makeOrder(OrderRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(request.getUsername()));
        Order order = new Order(user, request.getAddress(),
                EOrderStatus.Preparing, new Timestamp(System.currentTimeMillis()));
        ordersRepository.saveAndFlush(order);

        Set<OrderDetail> orderDetailSet = new HashSet<>();
        request.getProducts().forEach(orderProductRequest -> {
            Product product = productRepository.findById(orderProductRequest.getProductID())
                    .orElseThrow(() -> new RuntimeException("Error: Product not found."));
            OrderDetail orderDetail = new OrderDetail(order, product,
                    orderProductRequest.getQuantity(), product.getPrice());
            orderDetailSet.add(orderDetail);
        });
        orderDetailRepository.saveAll(orderDetailSet);
        return order;
    }

    @Override
    public Order changeStatus(OrderEditStatusRequest request) {
        Order order = ordersRepository.findById(request.getOrderID())
                .orElseThrow(() -> new OrderIdNotFoundException(request.getOrderID()));
        switch (request.getStatus().toLowerCase()){
            case "preparing":
                order.setStatus(EOrderStatus.Preparing);
                break;
            case "shipping":
                order.setStatus(EOrderStatus.Shipping);
                break;
            case "received":
                order.setStatus(EOrderStatus.Received);
                break;
        }
        return order;
    }

    @Override
    public List<Order> getOrdersByUsername(String username, Pageable pageable) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()  -> new UsernameNotFoundException(username));
        return ordersRepository.findByUser(user, pageable);
    }

    @Override
    public List<Order> getAll(Pageable pageable) {
        return ordersRepository.findAll(pageable).getContent();
    }

    @Override
    public List<OrderDetail> getOrderDetails(int orderID) {
        Order order = ordersRepository.findById(orderID)
                .orElseThrow(() -> new ProductIdNotFoundException(orderID));
        return orderDetailRepository.findByOrderObj(order);
    }
}
