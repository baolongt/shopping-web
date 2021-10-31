package nashtech.longtran.shoppingweb.controllers;

import nashtech.longtran.shoppingweb.payload.request.RatingRequest;
import nashtech.longtran.shoppingweb.services.implement.RatingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<?> getByProduct(@RequestParam Integer productID,
                                          @RequestParam Optional<Integer> page,
                                          @RequestParam Optional<Integer> offset){
        Pageable pageable = PageRequest.of(page.orElse(0), offset.orElse(10), Sort.by("id").ascending());
        return ResponseEntity.ok(ratingServiceImp.getByProductID(productID, pageable));
    }

}
