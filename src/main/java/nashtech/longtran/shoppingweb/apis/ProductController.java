package nashtech.longtran.shoppingweb.apis;

import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.payload.request.ProductAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ProductEditRequest;
import nashtech.longtran.shoppingweb.payload.response.MessageResponse;
import nashtech.longtran.shoppingweb.repository.CategoryRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/product/", method = RequestMethod.GET)
public class ProductController {
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam int page, @RequestParam int offset){
        Pageable pageable = PageRequest.of(page, offset);
        Page<Product> products = productRepo.findAll(pageable);
        return ResponseEntity
                .ok(new MessageResponse("success", products.getContent(), "get product sucess"));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductAddingRequest request){
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
                    Category category = categoryRepo.findById(categoryId)
                            .orElseThrow(() -> new RuntimeException("Error: Category not found."));;
                    categories.add(category);
                });
            }
            newProduct.setCategories(categories);
            productRepo.save(newProduct);
        }
        catch (Exception e){
            return ResponseEntity
                    .ok(new MessageResponse("error", null, "add new product fail"));
        }
        return ResponseEntity
                .ok(new MessageResponse("success", null, "add new product success"));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editProduct(@RequestBody ProductEditRequest request){
        Product product;
        try{
            product = productRepo.findById(request.getId()).orElseThrow(() -> new RuntimeException("Product not found."));
            product.setName(request.getProductName());
            product.setPrice(request.getPrice());
            product.setBrand(request.getBrand());
            product.setQuantity(request.getQuantity());
            product.setDetail(request.getDetail());
            product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            productRepo.save(product);
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity
                    .ok(new MessageResponse("error", null, "edit product fail"));
        }
        return ResponseEntity
                .ok(new MessageResponse("success", product, "edit product success"));
    }

    @GetMapping("/findByName")
    public ResponseEntity<?> findByName(
            @RequestParam String name,
            @RequestParam int page,
            @RequestParam int offset){
        Pageable pageable = PageRequest.of(page, offset);
        List<Product> products = productRepo.findAllByNameContaining(name, pageable);
        return ResponseEntity
                .ok(new MessageResponse("success", products, "get product success"));
    }
    //TODO get product by id
    @GetMapping("/getById")
    public ResponseEntity<?> getById(
            @RequestParam Integer id
    ){
        Product products = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found."));;;
        return ResponseEntity
                .ok(new MessageResponse("success", products, "get product success"));
    }
    //TODO filter by category
    @GetMapping("/getByCategory")
    public ResponseEntity<?> getByCategory(
            @RequestParam Integer[] categories,
            @RequestParam int page,
            @RequestParam int offset
    ){
        Set<Category> categorySet = new HashSet<>();
        Pageable pageable = PageRequest.of(page, offset);
        for (Integer categoryID : categories) {
            Category categoryObj = categoryRepo.findById(categoryID)
                    .orElseThrow(() -> new RuntimeException("Error: Category not found."));;
            categorySet.add(categoryObj);
        }
        List<Product> products = productRepo.findByCategoriesIn(categorySet, pageable);
        return ResponseEntity
               .ok(new MessageResponse("success", products, "get product success"));
    }
    //TODO filter by price range
    @GetMapping("/getByPriceRange")
    public ResponseEntity<?> getByPriceRange(
            @RequestParam Integer min,
            @RequestParam Integer max,
            @RequestParam int page,
            @RequestParam int offset
    ){
        Pageable pageable = PageRequest.of(page, offset);
        List<Product> products = productRepo.findByPriceBetween(min, max, pageable);
        return ResponseEntity
                .ok(new MessageResponse("success", products, "get product success"));
    }
    //TODO filter by rating

}
