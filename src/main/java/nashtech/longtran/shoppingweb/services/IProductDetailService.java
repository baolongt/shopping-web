package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.ProductDetail;
import nashtech.longtran.shoppingweb.payload.request.ProductAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ProductDetailAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ProductDetailEditRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductDetailService {
    ProductDetail addProductDetail(ProductDetailAddingRequest request);
    ProductDetail editProductDetail(ProductDetailEditRequest request);
    List<ProductDetail> getDetailsOfProduct(Integer productID);
    List<ProductDetail> getByPriceGreaterThan(float min , Pageable pageable);
    List<ProductDetail> getByPriceLessThan(float max , Pageable pageable);
    List<ProductDetail> getByPriceRange(float min, float max, Pageable pageable);
}
