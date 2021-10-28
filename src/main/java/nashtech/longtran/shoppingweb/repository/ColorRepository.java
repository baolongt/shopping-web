package nashtech.longtran.shoppingweb.repository;

import nashtech.longtran.shoppingweb.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
    Optional<Color> getColorById(int id);
}
