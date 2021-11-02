package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.OrderDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Order;
import nashtech.longtran.shoppingweb.entity.OrderDetail;
import nashtech.longtran.shoppingweb.payload.request.OrderEditStatusRequest;
import nashtech.longtran.shoppingweb.payload.request.OrderRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    ResponseDTO makeOrder(OrderDTO request);
    ResponseDTO changeStatus(OrderDTO request);
    ResponseDTO getOrdersByUsername(String username, Pageable pageable);
    ResponseDTO getAll(Pageable pageable);
    ResponseDTO getOrderDetails(int orderID);
}
