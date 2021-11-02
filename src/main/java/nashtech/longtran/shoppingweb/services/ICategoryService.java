package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.payload.request.CategoryEditRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    ResponseDTO getAll(Pageable pageable);
    ResponseDTO addCategory(String name, Integer parentID);
    ResponseDTO editCategory(CategoryEditRequest request);
}
