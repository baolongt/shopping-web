package nashtech.longtran.shoppingweb.service;

import nashtech.longtran.shoppingweb.entity.Rating;
import nashtech.longtran.shoppingweb.payload.request.RatingRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRatingService {
    Rating addRating(RatingRequest request);
    List<Rating> getByProductID(int productID, Pageable pageable);
}
