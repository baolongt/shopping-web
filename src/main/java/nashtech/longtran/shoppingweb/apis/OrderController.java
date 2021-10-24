package nashtech.longtran.shoppingweb.apis;

import nashtech.longtran.shoppingweb.entity.Order;
import nashtech.longtran.shoppingweb.entity.OrderDetail;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.payload.request.EditOrderStatusRequest;
import nashtech.longtran.shoppingweb.payload.request.OrderRequest;
import nashtech.longtran.shoppingweb.payload.response.MessageResponse;
import nashtech.longtran.shoppingweb.repository.OrderDetailRepository;
import nashtech.longtran.shoppingweb.repository.OrdersRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.repository.UserRepository;
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
    OrdersRepository orderRepo;

    @Autowired
    OrderDetailRepository orderDetailRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ProductRepository productRepo;

    @PostMapping("/makeOrder")
    public ResponseEntity<?> makeOrder(@RequestBody OrderRequest request){
        Order newOrder;
        try {
            User user = userRepo.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Error: Username not found."));
            Set<OrderDetail> orderDetailSet = new HashSet<>();
            newOrder = new Order(user, request.getAddress(), "Prepare", new Timestamp(System.currentTimeMillis()));
            orderRepo.save(newOrder);
            orderRepo.flush();
            request.getProducts().forEach(orderProductRequest -> {
                Product product = productRepo.findById(orderProductRequest.getProductID())
                        .orElseThrow(() -> new RuntimeException("Error: Product not found."));
                OrderDetail orderDetail = new OrderDetail(product, orderProductRequest.getQuantity(), product.getPrice());
                orderDetailSet.add(orderDetail);
            });
            orderDetailSet.forEach(product -> {
                product.setOrderObj(newOrder);
            });
            orderDetailRepo.saveAll(orderDetailSet);
        }
        catch (Exception e){
            return ResponseEntity
                    .ok(new MessageResponse("Error", null, e.getMessage()));
        }
        return ResponseEntity
                .ok(new MessageResponse("success", newOrder, "create order sucess"));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam int page,
                                    @RequestParam int offset){
        Pageable pageable = PageRequest.of(page, offset);
        Page<Order> orderList =  orderRepo.findAll(pageable);
        return ResponseEntity
                .ok(new MessageResponse("success", orderList.getContent(), "create order sucess"));
    }

    @PostMapping("changeStatus")
    public ResponseEntity<?> changeStatus(@RequestBody EditOrderStatusRequest request){
        try {
            Order order = orderRepo.findById(request.getOrderID()).orElseThrow(() -> new RuntimeException("Error: order not found."));
            order.setStatus(request.getStatus());
        }
        catch (Exception e){
                return ResponseEntity
                        .ok(new MessageResponse("Error", null, e.getMessage()));
            }
        return ResponseEntity
                .ok(new MessageResponse("success", null, "update status success"));
    }

    @GetMapping("viewOrders")
    public ResponseEntity<?> viewOrders(@RequestParam String username,
                                        @RequestParam int page,
                                        @RequestParam int offset){
        List<Order> orders;
        Pageable pageable = PageRequest.of(page, offset);
        try{
            User user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            orders = orderRepo.findByUser(user, pageable);
        }
        catch (Exception e){
            return ResponseEntity
                    .ok(new MessageResponse("Error", null, e.getMessage()));
        }
        return ResponseEntity
                .ok(new MessageResponse("success", orders, "get data success"));
    }

    @GetMapping("getDetail")
    public ResponseEntity<?> getDetail(@RequestParam int id){
        List<OrderDetail> orders;
        try{
            Order order = orderRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order not found"));
            orders = orderDetailRepo.findByOrderObj(order);
        }
        catch (Exception e){
            return ResponseEntity
                    .ok(new MessageResponse("Error", null, e.getMessage()));
        }
        return ResponseEntity
                .ok(new MessageResponse("success", orders, "get data success"));
    }
}
