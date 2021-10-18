package nashtech.longtran.shoppingweb.apis;

import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth", method = RequestMethod.GET)
public class ProductController {
    @Autowired
    private ProductRepository productRepo;

    @ResponseBody
    @GetMapping("/getProduct")
    public List<Product> getAll(){
        return  productRepo.findAll();
    }

    @ResponseBody
    @GetMapping("/addProduct")
    public Map<String, String> addProduct(@RequestParam String name){
        Map<String, String> map = new HashMap<>();
        try{
            productRepo.save(new Product(name));
            map.put("message","add new product successful");
        }
        catch (Exception e){
            map.put("message","add new product fail");
        }
        return map;
    }
}
