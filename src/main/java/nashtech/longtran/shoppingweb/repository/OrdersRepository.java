package nashtech.longtran.shoppingweb.repository;

import nashtech.longtran.shoppingweb.entity.Order;
import nashtech.longtran.shoppingweb.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findById(Integer id);
    List<Order> findByUser(User user, Pageable pageable);
}
