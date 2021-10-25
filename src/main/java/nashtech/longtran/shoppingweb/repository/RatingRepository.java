package nashtech.longtran.shoppingweb.repository;

import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Optional<Rating> findById(Integer id);
    List<Rating> findByProduct(Product product);
}
