package nashtech.longtran.shoppingweb.repository;

import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.ProductDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    Optional<ProductDetail> findById(Integer integer);
    List<ProductDetail> findByProductId(Integer id);
    List<ProductDetail> findByPriceBetween(float min, float max, Pageable pageable);
    List<ProductDetail> findByPriceGreaterThanEqual(float min, Pageable pageable);
    List<ProductDetail> findByPriceLessThanEqual(float max, Pageable pageable);
}
