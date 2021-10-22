package nashtech.longtran.shoppingweb.apis;

import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.Rating;
import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.payload.request.RatingRequest;
import nashtech.longtran.shoppingweb.payload.response.MessageResponse;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.repository.RatingRepository;
import nashtech.longtran.shoppingweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/rating/", method = RequestMethod.GET)
public class RatingController {
    @Autowired
    RatingRepository ratingRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    UserRepository userRepo;
// TODO only for user who buy it
    @GetMapping("/add")
    public ResponseEntity<?> addNewRating(@RequestBody RatingRequest request){
        Rating newRating;
        try{
            User user = userRepo.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Error: Username not found."));
            Product product = productRepo.findById(request.getProductID())
                    .orElseThrow(() -> new RuntimeException("Error: Product not found."));
            newRating = new Rating(user, product,
                    request.getRatingPoint(), request.getRatingContent());
            ratingRepo.save(newRating);
        }
        catch (Exception e){
            return ResponseEntity
                    .ok(new MessageResponse("error", null, "add rating fail"));
        }
        return ResponseEntity
                .ok(new MessageResponse("success", newRating, "add rating sucess"));
    }
}
