package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.CategoryDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    ResponseDTO getAll(Pageable pageable);
    ResponseDTO addCategory(String name, Integer parentID);
    ResponseDTO editCategory(CategoryDTO request);
}
