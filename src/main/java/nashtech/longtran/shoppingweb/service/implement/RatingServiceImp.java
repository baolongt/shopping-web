package nashtech.longtran.shoppingweb.service.implement;

import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.Rating;
import nashtech.longtran.shoppingweb.entity.User;
import nashtech.longtran.shoppingweb.exception.ProductIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.RatingRequest;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.repository.RatingRepository;
import nashtech.longtran.shoppingweb.repository.UserRepository;
import nashtech.longtran.shoppingweb.service.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImp implements IRatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Rating addRating(RatingRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()  -> new UsernameNotFoundException(request.getUsername()));
        Product product  =  productRepository.findById(request.getProductID())
                .orElseThrow(() -> new ProductIdNotFoundException(request.getProductID()));
        Rating rating = new Rating(user, product, request.getRatingPoint(),  request.getRatingContent());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getByProductID(int productID, Pageable pageable) {
        Product product  = productRepository.findById(productID)
                .orElseThrow(()  -> new ProductIdNotFoundException(productID));
        return ratingRepository.findByProduct(product, pageable);
    }
}
