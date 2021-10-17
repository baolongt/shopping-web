package nashtech.longtran.shoppingweb.repository;

import nashtech.longtran.shoppingweb.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
