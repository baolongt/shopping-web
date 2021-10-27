package nashtech.longtran.shoppingweb.service.implement;

import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.exception.CategoryIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.EmptyCategoryNameException;
import nashtech.longtran.shoppingweb.repository.CategoryRepository;
import nashtech.longtran.shoppingweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).getContent();
    }

    @Override
    public Category addCategory(String name) {
        if (name == null || name.isEmpty()) {
            throw new EmptyCategoryNameException();
        }
        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    @Override
    public Category editCategory(int categoryID, String nameEdit) {
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() -> new CategoryIdNotFoundException(categoryID));
        return categoryRepository.save(category);
    }
}
