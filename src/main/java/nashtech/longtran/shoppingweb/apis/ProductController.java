package nashtech.longtran.shoppingweb.apis;

import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.payload.request.ProductAddingRequest;
import nashtech.longtran.shoppingweb.repository.CategoryRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1", method = RequestMethod.GET)
public class ProductController {
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @ResponseBody
    @GetMapping("/getProduct")
    public List<Product> getAll(){
        return  productRepo.findAll();
    }

    // TODO add product
    @PostMapping("/addProduct")
    public Map<String, Object> addProduct(@RequestBody ProductAddingRequest request){
        Map<String, Object> map = new HashMap<>();
        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Product newProduct = new Product(request.getProductName(),request.getPrice(),
                    request.getQuantity(), request.getBrand(),
                    request.getDetail(), timestamp, timestamp);
            Set<Integer> strCategories = request.getCategories();
            Set<Category> categories = new HashSet<>();
            if(strCategories == null){
                strCategories = new HashSet<>();
            } else {
                strCategories.forEach(categoryId -> {
                    Category category = categoryRepo.findCategoryId(categoryId)
                            .orElseThrow(() -> new RuntimeException("Error: Category not found."));;
                    categories.add(category);
                });
            }
            newProduct.setCategories(categories);
            productRepo.save(newProduct);
            map.put("message", "add new product success");
        }
        catch (Exception e){
            map.put("message","add new product fail");
        }
        return map;
    }

}
