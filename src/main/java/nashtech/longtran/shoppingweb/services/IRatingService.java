package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Rating;
import nashtech.longtran.shoppingweb.payload.request.RatingRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRatingService {
    ResponseDTO addRating(RatingRequest request);
    ResponseDTO getByProductID(int productID, Pageable pageable);
}
