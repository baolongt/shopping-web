package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.constant.SuccessCode;
import nashtech.longtran.shoppingweb.dto.RatingDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.Rating;
import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.exception.ProductIdNotFoundException;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.repository.RatingRepository;
import nashtech.longtran.shoppingweb.repository.UserRepository;
import nashtech.longtran.shoppingweb.services.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImp implements IRatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseDTO addRating(RatingDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()  -> new UsernameNotFoundException(request.getUsername()));
        Product product  =  productRepository.findById(request.getProductID())
                .orElseThrow(() -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));
        try {
            Rating rating = new Rating(user, product, request.getRatingPoint(), request.getRatingContent());
            ratingRepository.save(rating);
            responseDTO.setSuccessCode(SuccessCode.ADD_RATING_SUCCESS);
        }
        catch (Exception e){
            responseDTO.setErrorCode(ErrorCode.ERR_SAVE_RATING);
        }
        return  responseDTO;
    }

    @Override
    public ResponseDTO getByProductID(int productID, Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        Product product  = productRepository.findById(productID)
                .orElseThrow(()  -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));
        responseDTO.setData(ratingRepository.findByProduct(product, pageable));
        return responseDTO;
    }
}
