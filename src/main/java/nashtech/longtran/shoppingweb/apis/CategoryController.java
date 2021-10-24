package nashtech.longtran.shoppingweb.apis;

import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.payload.response.MessageResponse;
import nashtech.longtran.shoppingweb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category/", method = RequestMethod.GET)
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepo;

    @GetMapping("/getAll")
    public ResponseEntity<?> getCategoryById(){
        List<Category> categories = categoryRepo.findAll();
        return ResponseEntity
                .ok(new MessageResponse("success", categories, "get categories success"));
    }

    @GetMapping("/add")
    public ResponseEntity<?> addCategory(
        @RequestParam String name
    ){
        try{
            Category category = new Category(name);
            categoryRepo.save(category);
        }
        catch (Exception e){
            return ResponseEntity
                    .ok(new MessageResponse("error", null, "add category fail"));
        }
        return ResponseEntity
                .ok(new MessageResponse("success", null, "add category success"));
    }

    @GetMapping("/edit")
    public ResponseEntity<?> editCategory(
            @RequestParam Integer id,
            @RequestParam String name
    ){
        try{
            Category category = categoryRepo.getById(id);
            categoryRepo.save(category);
        }
        catch (Exception e){
            return ResponseEntity
                    .ok(new MessageResponse("error", null, "add category fail"));
        }
        return ResponseEntity
                .ok(new MessageResponse("success", null, "add category success"));
    }


}
