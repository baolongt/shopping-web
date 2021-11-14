package nashtech.longtran.shoppingweb.repository.specification;

import nashtech.longtran.shoppingweb.dto.ProductDTO;
import nashtech.longtran.shoppingweb.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSpecification {
    public Specification<Product> getProducts(ProductDTO request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null && !request.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + request.getName().toLowerCase() + "%"));
            }
            query.orderBy(criteriaBuilder.desc(root.get("experience")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
