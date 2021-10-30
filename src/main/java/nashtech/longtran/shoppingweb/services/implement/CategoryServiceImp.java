package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.exception.CategoryIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.EmptyCategoryNameException;
import nashtech.longtran.shoppingweb.payload.request.CategoryEditRequest;
import nashtech.longtran.shoppingweb.repository.CategoryRepository;
import nashtech.longtran.shoppingweb.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).getContent();
    }

    @Override
    public Category addCategory(String name, Integer parentID) {
        if (name == null || !name.isEmpty()) {
            throw new EmptyCategoryNameException();
        }
        Category category = parentID ==  null ? new Category(name): new Category(name, parentID);
        return categoryRepository.save(category);
    }

    @Override
    public Category editCategory(CategoryEditRequest request) {
        Category category = categoryRepository.findById(request.getCategoryID())
                .orElseThrow(() -> new CategoryIdNotFoundException(request.getCategoryID()));
        category.setName(request.getName());
        category.setParentID(request.getParentID());
        return categoryRepository.save(category);
    }
}
