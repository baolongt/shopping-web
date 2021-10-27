package nashtech.longtran.shoppingweb.restController;

import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.payload.response.MessageResponse;
import nashtech.longtran.shoppingweb.repository.CategoryRepository;
import nashtech.longtran.shoppingweb.service.implement.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/category/", method = RequestMethod.GET)
public class CategoryController {

    @Autowired
    CategoryServiceImp categoryServiceImp;

    @GetMapping("/getAll")
    public ResponseEntity<?> getCategoryById(@RequestParam Optional<Integer> page,
                                             @RequestParam Optional<Integer>  offset){
        Pageable pageable = PageRequest.of(page.orElse(0), offset.orElse(10));
        return ResponseEntity.ok(categoryServiceImp.getAll(pageable));
    }

    @GetMapping("/add")
    public ResponseEntity<?> addCategory(
        @RequestParam String name
    ){
        return ResponseEntity.ok(categoryServiceImp.addCategory(name));
    }

    @GetMapping("/edit")
    public ResponseEntity<?> editCategory(
            @RequestParam Integer id,
            @RequestParam String name
    ){
        return ResponseEntity.ok(categoryServiceImp.editCategory(id, name));
    }


}
