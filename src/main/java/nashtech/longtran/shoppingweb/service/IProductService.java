package nashtech.longtran.shoppingweb.service;

import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.payload.request.ProductAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ProductEditRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<Product> getAll(Pageable pageable);
    Product addProduct(ProductAddingRequest product);
    Product editProduct(ProductEditRequest product);
    Product getByID(int productID);
    List<Product> findByName(String name, Pageable pageable);
    List<Product> getByCategory(int categoryID , Pageable pageable);
    List<Product> getByPriceGreaterThan(float min , Pageable pageable);
    List<Product> getByPriceLessThan(float max , Pageable pageable);
    List<Product> getByPriceRange(float min, float max, Pageable pageable);
}
