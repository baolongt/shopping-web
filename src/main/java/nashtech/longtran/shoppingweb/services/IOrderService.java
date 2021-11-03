package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.OrderDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    ResponseDTO makeOrder(OrderDTO request);
    ResponseDTO changeStatus(OrderDTO request);
    ResponseDTO getOrdersByUsername(String username, Pageable pageable);
    ResponseDTO getAll(Pageable pageable);
    ResponseDTO getOrderDetails(int orderID);
}
