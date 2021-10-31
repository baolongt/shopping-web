package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.exception.BrandIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.ProductAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ProductEditRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface IProductService {
    List<Product> getAll(Pageable pageable);
    Product addProduct(ProductAddingRequest product) throws BrandIdNotFoundException;
    Product editProduct(ProductEditRequest product);
    Product getByID(int productID);
    List<Product> findByName(String name, Pageable pageable);
    List<Product> getByCategories(int[] categoryID , Pageable pageable);
}
