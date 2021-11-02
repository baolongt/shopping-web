package nashtech.longtran.shoppingweb.restcontroller;

import nashtech.longtran.shoppingweb.dto.ProductDTO;
import nashtech.longtran.shoppingweb.payload.request.ProductAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ProductEditRequest;
import nashtech.longtran.shoppingweb.services.implement.ProductDetailServiceImp;
import nashtech.longtran.shoppingweb.services.implement.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/product/", method = RequestMethod.GET)
public class ProductController {
    @Autowired
    ProductServiceImp productServiceImp;

    @Autowired
    ProductDetailServiceImp productDetailServiceImp;

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
        return ResponseEntity.ok(productServiceImp.getAll(pageable));
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO request){
           return ResponseEntity.ok(productServiceImp.addProduct(request));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editProduct(@RequestBody ProductDTO request){
       return ResponseEntity.ok(productServiceImp.editProduct(request));
    }

    @GetMapping("/findByName")
    public ResponseEntity<?> findByName(@RequestParam String name, @RequestParam int page, @RequestParam int offset){
        Pageable pageable = PageRequest.of(page, offset);
        return ResponseEntity.ok(productServiceImp.findByName(name, pageable));
    }
    @GetMapping("/getById")
    public ResponseEntity<?> getById(
            @RequestParam Integer id
    ){
        return ResponseEntity.ok(productServiceImp.getByID(id));
    }
    @GetMapping("/getByCategory")
    public ResponseEntity<?> getByCategory(
            @RequestParam int[] categoryId,
            @RequestParam int page,
            @RequestParam int offset
    ){
        Pageable pageable = PageRequest.of(page, offset);
        return ResponseEntity.ok(productServiceImp.getByCategories(categoryId, pageable));
    }
    @GetMapping("/getByPriceRange")
    public ResponseEntity<?> getByPriceRange(
            @RequestParam Integer min,
            @RequestParam Integer max,
            @RequestParam int page,
            @RequestParam int offset
    ){
        Pageable pageable = PageRequest.of(page, offset);
        if(min ==  null){
            return ResponseEntity.ok(productDetailServiceImp.getByPriceLessThan(max, pageable));
        }
        else if(max == null){
            return ResponseEntity.ok(productDetailServiceImp.getByPriceGreaterThan(min, pageable));
        }
        else return ResponseEntity.ok(productDetailServiceImp.getByPriceRange(min, max, pageable));
    }

}
