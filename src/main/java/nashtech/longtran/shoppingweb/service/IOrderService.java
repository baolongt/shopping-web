package nashtech.longtran.shoppingweb.service;

import nashtech.longtran.shoppingweb.entity.Order;
import nashtech.longtran.shoppingweb.entity.OrderDetail;
import nashtech.longtran.shoppingweb.payload.request.EditOrderStatusRequest;
import nashtech.longtran.shoppingweb.payload.request.OrderRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    Order makeOrder(OrderRequest request);
    Order changeStatus(EditOrderStatusRequest request);
    List<Order> getOrdersByUsername(String username, Pageable pageable);
    List<Order> getAll(Pageable pageable);
    List<OrderDetail> getOrderDetails(int orderID);
}
