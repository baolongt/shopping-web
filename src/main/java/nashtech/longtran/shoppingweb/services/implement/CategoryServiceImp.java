package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.constant.SuccessCode;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.exception.CategoryIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.CategoryEditRequest;
import nashtech.longtran.shoppingweb.repository.CategoryRepository;
import nashtech.longtran.shoppingweb.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ResponseDTO getAll(Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(categoryRepository.findAll(pageable).getContent());
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_CATEGORY_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO addCategory(String name, Integer parentID) {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = parentID ==  null ? new Category(name): new Category(name, parentID);
        try {
            categoryRepository.save(category);
            responseDTO.setSuccessCode(SuccessCode.ADD_CATEGORY_SUCCESS);
        }
        catch (Exception e){
            responseDTO.setErrorCode(ErrorCode.ERR_SAVE_CATEGORY);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO editCategory(CategoryEditRequest request) {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryRepository.findById(request.getCategoryID())
                .orElseThrow(() -> new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND));
        try {
            category.setName(request.getName());
            category.setParentID(request.getParentID());
            categoryRepository.save(category);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_CATEGORY_SUCCESS);
        }
        catch (Exception e)
        {
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_CATEGORY);
        }
        return responseDTO;
    }
}
