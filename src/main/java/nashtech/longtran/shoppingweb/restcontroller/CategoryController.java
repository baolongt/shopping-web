package nashtech.longtran.shoppingweb.restcontroller;

import nashtech.longtran.shoppingweb.dto.CategoryDTO;
import nashtech.longtran.shoppingweb.services.implement.CategoryServiceImp;
import net.bytebuddy.TypeCache;
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
@RequestMapping(value = "/api/v1/category/")
public class CategoryController {

    @Autowired
    CategoryServiceImp categoryServiceImp;

    @GetMapping("/getAll")
    public ResponseEntity<?> getCategoryById(){
        return ResponseEntity.ok(categoryServiceImp.getAll());
    }

    @GetMapping("/getParent")
    public ResponseEntity<?> getParentCategory(){
        return ResponseEntity.ok(categoryServiceImp.getParentCategory());
    }

    @GetMapping("/getSubCategory")
    public ResponseEntity<?> getSubCategory(@RequestParam Integer parentId){
        return ResponseEntity.ok(categoryServiceImp.getChildCategory(parentId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(
        @RequestBody String name,
        @RequestBody Integer parentID
    ){
        return ResponseEntity.ok(categoryServiceImp.addCategory(name, parentID));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editCategory(@RequestBody @Valid CategoryDTO request){
        return ResponseEntity.ok(categoryServiceImp.editCategory(request));
    }


}
