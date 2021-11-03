package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.ProductDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    ResponseDTO getAll(Pageable pageable);
    ResponseDTO addProduct(ProductDTO product);
    ResponseDTO editProduct(ProductDTO product);
    ResponseDTO getByID(int productID);
    ResponseDTO findByName(String name, Pageable pageable);
    ResponseDTO getByCategories(int[] categoryID , Pageable pageable);
}
