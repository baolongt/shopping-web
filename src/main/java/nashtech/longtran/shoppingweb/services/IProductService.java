package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.ProductDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.exception.BrandIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.ProductAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ProductEditRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface IProductService {
    ResponseDTO getAll(Pageable pageable);
    ResponseDTO addProduct(ProductDTO product);
    ResponseDTO editProduct(ProductDTO product);
    ResponseDTO getByID(int productID);
    ResponseDTO findByName(String name, Pageable pageable);
    ResponseDTO getByCategories(int[] categoryID , Pageable pageable);
}
