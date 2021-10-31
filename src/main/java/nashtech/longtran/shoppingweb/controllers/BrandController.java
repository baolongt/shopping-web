package nashtech.longtran.shoppingweb.controllers;

import nashtech.longtran.shoppingweb.entity.Order;
import nashtech.longtran.shoppingweb.payload.request.BrandAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.BrandEditRequest;
import nashtech.longtran.shoppingweb.services.implement.BrandServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/brand/")
public class BrandController {

    @Autowired
    BrandServiceImp brandServiceImp;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(
            @RequestParam Optional<Integer> page,
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
        return ResponseEntity.ok(brandServiceImp.getAll(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewBrand(
            @RequestBody @Valid BrandAddingRequest request
            ){
        return ResponseEntity.ok(brandServiceImp.addNewBrand(request));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editBrand(
            @RequestBody BrandEditRequest request
            ){
        return ResponseEntity.ok(brandServiceImp.editBrand(request));
    }

}
