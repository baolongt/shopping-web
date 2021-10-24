package nashtech.longtran.shoppingweb.repository;

import nashtech.longtran.shoppingweb.entity.Order;
import nashtech.longtran.shoppingweb.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrderObj(Order order);
}
