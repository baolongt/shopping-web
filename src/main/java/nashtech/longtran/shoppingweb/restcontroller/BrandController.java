package nashtech.longtran.shoppingweb.restcontroller;

import nashtech.longtran.shoppingweb.dto.BrandDTO;
import nashtech.longtran.shoppingweb.services.implement.BrandServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/brand/")
public class BrandController {

    @Autowired
    BrandServiceImp brandServiceImp;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(brandServiceImp.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewBrand(
            @RequestBody @Valid BrandDTO request
            ){
        return ResponseEntity.ok(brandServiceImp.addNewBrand(request));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editBrand(
            @RequestBody @Valid BrandDTO request
            ){
        return ResponseEntity.ok(brandServiceImp.editBrand(request));
    }

}
