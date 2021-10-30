package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.payload.request.CategoryEditRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll(Pageable pageable);
    Category addCategory(String name, Integer parentID);
    Category editCategory(CategoryEditRequest request);
}
