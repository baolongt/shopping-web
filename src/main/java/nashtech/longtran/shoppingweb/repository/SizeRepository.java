package nashtech.longtran.shoppingweb.repository;

import nashtech.longtran.shoppingweb.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
    Optional<Size> getSizeById(int id);
}
