package nashtech.longtran.shoppingweb.apis;

import nashtech.longtran.shoppingweb.entity.Order;
import nashtech.longtran.shoppingweb.entity.OrderDetail;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.payload.request.OrderProductRequest;
import nashtech.longtran.shoppingweb.payload.request.OrderRequest;
import nashtech.longtran.shoppingweb.payload.response.MessageResponse;
import nashtech.longtran.shoppingweb.repository.OrderDetailRepository;
import nashtech.longtran.shoppingweb.repository.OrderRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/order/", method = RequestMethod.GET)
public class OrderController {

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderDetailRepository orderDetailRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ProductRepository productRepo;

    @GetMapping("/makeOrder")
    public ResponseEntity<?> makeOrder(@RequestBody OrderRequest request){
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: Username not found."));
        Set<OrderDetail> orderDetailSet = new HashSet<>();
        Order order = new Order(user, request.getAddress(), "Prepare", new Timestamp(System.currentTimeMillis()));
        for (OrderProductRequest orderProductRequest : request.getProducts()) {
            Product product = productRepo.findById(orderProductRequest.getProductID())
                    .orElseThrow(() -> new RuntimeException("Error: product not found."));
            OrderDetail orderDetail = new OrderDetail(product , orderProductRequest.getQuantity(), product.getPrice());
            orderDetailSet.add(orderDetail);
        });
        order.setProducts(orderDetailSet);
        orderRepo.save(order);
        orderRepo.flush();
        orderDetailSet.forEach(product -> {
            product.setOrder(order);
        });
        orderDetailRepo.saveAll(orderDetailSet);
        return ResponseEntity
                .ok(new MessageResponse("success", order, "get product sucess"));
    }
}
