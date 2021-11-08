package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.CategoryDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    ResponseDTO getParentCategory();
    ResponseDTO getChildCategory(Integer parentID);
    ResponseDTO getAll();
    ResponseDTO addCategory(String name, Integer parentID);
    ResponseDTO editCategory(CategoryDTO request);
}
