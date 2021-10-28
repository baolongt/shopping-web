package nashtech.longtran.shoppingweb.repository;

import nashtech.longtran.shoppingweb.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Optional<Brand> getBrandById(Integer id);
}
