package nashtech.longtran.shoppingweb.controllers;

import nashtech.longtran.shoppingweb.payload.request.EditOrderStatusRequest;
import nashtech.longtran.shoppingweb.payload.request.OrderRequest;
import nashtech.longtran.shoppingweb.services.implement.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
