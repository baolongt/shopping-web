package nashtech.longtran.shoppingweb.repository;

import nashtech.longtran.shoppingweb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findCategoryId(Integer id);
}
