package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.constant.SuccessCode;
import nashtech.longtran.shoppingweb.dto.OrderDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.*;
import nashtech.longtran.shoppingweb.enums.EOrderStatus;
import nashtech.longtran.shoppingweb.exception.OrderIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.ProductDetailIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.ProductIdNotFoundException;
import nashtech.longtran.shoppingweb.repository.*;
import nashtech.longtran.shoppingweb.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
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

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public ResponseDTO makeOrder(OrderDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(request.getUsername()));
        Order order = new Order(user, request.getAddress(),
                EOrderStatus.Preparing, new Timestamp(System.currentTimeMillis()));
        Set<OrderDetail> orderDetailSet = new HashSet<>();
        try {
            ordersRepository.saveAndFlush(order);
            request.getProducts().forEach(orderProductRequest -> {
                ProductDetail product = productDetailRepository.findById(orderProductRequest.getProductDetailID())
                        .orElseThrow(() -> new ProductDetailIdNotFoundException(ErrorCode.ERR_PRODUCT_DETAIL_ID_NOT_FOUND));
                OrderDetail orderDetail = new OrderDetail(order, product,
                        orderProductRequest.getQuantity(), product.getPrice());
                orderDetailSet.add(orderDetail);
            });
            orderDetailRepository.saveAll(orderDetailSet);
            responseDTO.setSuccessCode(SuccessCode.ADD_ORDER_SUCCESS);
        }
        catch (Exception e){
            responseDTO.setErrorCode(ErrorCode.ERR_SAVE_ORDER);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO changeStatus(OrderDTO request) {
        ResponseDTO responseDTO  = new ResponseDTO();
        Order order = ordersRepository.findById(request.getId())
                .orElseThrow(() -> new OrderIdNotFoundException(ErrorCode.ERR_ORDER_ID_NOT_FOUND));
        try {
            switch (request.getStatus().toLowerCase()) {
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
            ordersRepository.save(order);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_ORDER_SUCCESS);
        }
        catch (Exception e){
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_ORDER);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getOrdersByUsername(String username, Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        User user = userRepository.findByUsername(username)
                .orElseThrow(()  -> new UsernameNotFoundException(username));
            responseDTO.setData(ordersRepository.findByUser(user, pageable));
            responseDTO.setData(SuccessCode.RETRIEVE_ORDER_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO getAll(Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(ordersRepository.findAll(pageable));
        responseDTO.setData(SuccessCode.RETRIEVE_ORDER_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO getOrderDetails(int orderID) {
        ResponseDTO responseDTO = new ResponseDTO();
        Order order = ordersRepository.findById(orderID)
                .orElseThrow(() -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));
        responseDTO.setData(orderDetailRepository.findByOrderObj(order));
        responseDTO.setData(SuccessCode.RETRIEVE_ORDER_SUCCESS);
        return responseDTO;
    }
}
