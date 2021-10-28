package nashtech.longtran.shoppingweb.controllers;

import nashtech.longtran.shoppingweb.payload.request.BrandAddingRequest;
import nashtech.longtran.shoppingweb.services.implement.BrandServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/brand/")
public class BrandController {

    @Autowired
    BrandServiceImp brandServiceImp;

    @GetMapping("/add")
    public ResponseEntity<?> addCategory(
            @RequestParam String name
            ){
        return ResponseEntity.ok(brandServiceImp.addNewBrand(name));
    }

    @GetMapping("/edit")
    public ResponseEntity<?> editCategory(
            @RequestParam Integer id,
            @RequestParam String name
    ){
        return ResponseEntity.ok(brandServiceImp.editBrand(id, name));
    }

}
