package nashtech.longtran.shoppingweb.restController;

import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.Rating;
import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.payload.request.RatingRequest;
import nashtech.longtran.shoppingweb.payload.response.MessageResponse;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.repository.RatingRepository;
import nashtech.longtran.shoppingweb.repository.UserRepository;
import nashtech.longtran.shoppingweb.service.implement.RatingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/rating/", method = RequestMethod.GET)
public class RatingController {

    @Autowired
    RatingServiceImp ratingServiceImp;

    @PostMapping("/add")
    public ResponseEntity<?> addNewRating(@RequestBody RatingRequest request){
        return ResponseEntity.ok(ratingServiceImp.addRating(request));
    }

    @GetMapping("/getByProduct")
    public ResponseEntity<?> getByProduct(@RequestParam int productID,
                                          @RequestParam int page,
                                          @RequestParam int offset){
        Pageable pageable = PageRequest.of(page, offset);
        return ResponseEntity.ok(ratingServiceImp.getByProductID(productID, pageable));
    }

}
