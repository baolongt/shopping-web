package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.ProductDetailDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IProductDetailService {
    ResponseDTO addProductDetail(ProductDetailDTO request);
    ResponseDTO editProductDetail(ProductDetailDTO request);
    ResponseDTO getDetailsOfProduct(Integer productID);
    ResponseDTO getByPriceGreaterThan(float min , Pageable pageable);
    ResponseDTO  getByPriceLessThan(float max , Pageable pageable);
    ResponseDTO  getByPriceRange(float min, float max, Pageable pageable);
}
