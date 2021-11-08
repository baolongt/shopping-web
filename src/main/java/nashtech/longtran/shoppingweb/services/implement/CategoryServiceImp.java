package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.constant.SuccessCode;
import nashtech.longtran.shoppingweb.dto.CategoryDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.exception.CategoryIdNotFoundException;
import nashtech.longtran.shoppingweb.repository.CategoryRepository;
import nashtech.longtran.shoppingweb.services.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseDTO getParentCategory() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<CategoryDTO> categoryList = categoryRepository
                .findByParentIDIsNull()
                .stream()
                .map(c -> modelMapper.map(c, CategoryDTO.class))
                .collect(Collectors.toList());
        responseDTO.setData(categoryList);
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_CATEGORY_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO getChildCategory(Integer parentID) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(categoryRepository.findByParentID(parentID));
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_CATEGORY_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO getAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(categoryRepository.findAll());
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
    public ResponseDTO editCategory(CategoryDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryRepository.findById(request.getId())
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
