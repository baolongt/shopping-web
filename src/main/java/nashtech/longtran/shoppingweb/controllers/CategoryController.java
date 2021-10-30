package nashtech.longtran.shoppingweb.controllers;

import nashtech.longtran.shoppingweb.payload.request.CategoryEditRequest;
import nashtech.longtran.shoppingweb.services.implement.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/category/")
public class CategoryController {

    @Autowired
    CategoryServiceImp categoryServiceImp;

    @GetMapping("/getAll")
    public ResponseEntity<?> getCategoryById(@RequestParam Optional<Integer> page,
                                             @RequestParam Optional<Integer>  offset){
        Pageable pageable = PageRequest.of(page.orElse(0), offset.orElse(10));
        return ResponseEntity.ok(categoryServiceImp.getAll(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(
        @RequestBody String name,
        @RequestBody Integer parentID
    ){
        return ResponseEntity.ok(categoryServiceImp.addCategory(name, parentID));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editCategory(@RequestBody CategoryEditRequest request){
        return ResponseEntity.ok(categoryServiceImp.editCategory(request));
    }


}
