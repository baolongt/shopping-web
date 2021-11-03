package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.RatingDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IRatingService {
    ResponseDTO addRating(RatingDTO request);
    ResponseDTO getByProductID(int productID, Pageable pageable);
}
