package nashtech.longtran.shoppingweb.controllers;

import nashtech.longtran.shoppingweb.payload.request.ProductAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ProductEditRequest;
import nashtech.longtran.shoppingweb.services.implement.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/product/", method = RequestMethod.GET)
public class ProductController {
    @Autowired
    ProductServiceImp productServiceImp;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam int page, @RequestParam int offset){
        Pageable pageable = PageRequest.of(page, offset);
        return ResponseEntity.ok(productServiceImp.getAll(pageable));
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductAddingRequest request){
           return ResponseEntity.ok(productServiceImp.addProduct(request));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editProduct(@RequestBody ProductEditRequest request){
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
            @RequestParam Integer categoryId,
            @RequestParam int page,
            @RequestParam int offset
    ){
        Pageable pageable = PageRequest.of(page, offset);
        return ResponseEntity.ok(productServiceImp.getByCategory(categoryId, pageable));
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
            return ResponseEntity.ok(productServiceImp.getByPriceLessThan(max, pageable));
        }
        else if(max == null){
            return ResponseEntity.ok(productServiceImp.getByPriceGreaterThan(min, pageable));
        }
        else return ResponseEntity.ok(productServiceImp.getByPriceRange(min, max, pageable));
    }

}
