package nashtech.longtran.shoppingweb.restcontroller;

import nashtech.longtran.shoppingweb.dto.OrderDTO;
import nashtech.longtran.shoppingweb.payload.request.OrderEditStatusRequest;
import nashtech.longtran.shoppingweb.payload.request.OrderRequest;
import nashtech.longtran.shoppingweb.services.implement.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/order/")
public class OrderController {

    @Autowired
    OrderServiceImp orderServiceImp;

    @PostMapping("/makeOrder")
    public ResponseEntity<?> makeOrder(@RequestBody OrderDTO request){
        return  ResponseEntity.ok( orderServiceImp.makeOrder(request));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam Optional<Integer> page,
                                    @RequestParam Optional<Integer> offset,
                                    @RequestParam Optional<String> sortBy,
                                    @RequestParam Optional<String> order){
        Pageable pageable;
        if (order.orElse("").toLowerCase().equals("desc")) {
            pageable = PageRequest.of(page.orElse(0),
                    offset.orElse(10),
                    Sort.by(sortBy.orElse("id")).descending());
        } else {
            pageable = PageRequest.of(page.orElse(0),
                    offset.orElse(10),
                    Sort.by(sortBy.orElse("id")).ascending());
        }
        return ResponseEntity.ok(orderServiceImp.getAll(pageable));
    }

    @PostMapping("changeStatus")
    public ResponseEntity<?> changeStatus(@RequestBody OrderDTO request){
        return ResponseEntity.ok(orderServiceImp.changeStatus(request));
    }

    @GetMapping("getOrders")
    public ResponseEntity<?> getOrdersOfUsername(
            @RequestParam String username,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> offset){
        Pageable pageable = PageRequest.of(page.orElse(0), offset.orElse(10));
        return ResponseEntity.ok(orderServiceImp.getOrdersByUsername(username, pageable));
    }

    @GetMapping("getDetail")
    public ResponseEntity<?> getDetail(@RequestParam int id){
        return ResponseEntity.ok(orderServiceImp.getOrderDetails(id));
    }
}
