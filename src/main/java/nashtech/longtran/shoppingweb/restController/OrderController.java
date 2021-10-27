package nashtech.longtran.shoppingweb.restController;

import nashtech.longtran.shoppingweb.entity.Order;
import nashtech.longtran.shoppingweb.entity.OrderDetail;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.exception.ProductIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.EditOrderStatusRequest;
import nashtech.longtran.shoppingweb.payload.request.OrderRequest;
import nashtech.longtran.shoppingweb.payload.response.MessageResponse;
import nashtech.longtran.shoppingweb.repository.OrderDetailRepository;
import nashtech.longtran.shoppingweb.repository.OrdersRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.repository.UserRepository;
import nashtech.longtran.shoppingweb.service.implement.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/order/")
public class OrderController {

    @Autowired
    OrderServiceImp orderServiceImp;

    @PostMapping("/makeOrder")
    public ResponseEntity<?> makeOrder(@RequestBody OrderRequest request){
        return  ResponseEntity.ok( orderServiceImp.makeOrder(request));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam int page, @RequestParam int offset){
        Pageable pageable = PageRequest.of(page, offset);
        return ResponseEntity.ok(orderServiceImp.getAll(pageable));
    }

    @PostMapping("changeStatus")
    public ResponseEntity<?> changeStatus(@RequestBody EditOrderStatusRequest request){
        return ResponseEntity.ok(orderServiceImp.changeStatus(request));
    }

    @GetMapping("getOrders")
    public ResponseEntity<?> getOrdersOfUsername(
            @RequestParam String username,
            @RequestParam int page,
            @RequestParam int offset){
        Pageable pageable = PageRequest.of(page, offset);
        return ResponseEntity.ok(orderServiceImp.getOrdersByUsername(username, pageable));
    }

    @GetMapping("getDetail")
    public ResponseEntity<?> getDetail(@RequestParam int id){
        return ResponseEntity.ok(orderServiceImp.getOrderDetails(id));
    }
}
