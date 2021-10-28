package nashtech.longtran.shoppingweb.repository;

import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByNameContaining(String name, Pageable pageable);
    Optional<Product> findById(Integer id);
    List<Product> findByCategoriesIn(Collection<Category> categories, Pageable pageable);
    List<Product> findByPriceBetween(float min, float max, Pageable pageable);
    List<Product> findByPriceGreaterThanEqual(float min, Pageable pageable);
    List<Product> findByPriceLessThanEqual(float max, Pageable pageable);
}
