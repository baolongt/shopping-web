package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.entity.Color;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.ProductDetail;
import nashtech.longtran.shoppingweb.entity.Size;
import nashtech.longtran.shoppingweb.exception.ColorIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.ProductIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.SizeIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.ProductAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ProductDetailAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ProductDetailEditRequest;
import nashtech.longtran.shoppingweb.repository.ColorRepository;
import nashtech.longtran.shoppingweb.repository.ProductDetailRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.repository.SizeRepository;
import nashtech.longtran.shoppingweb.services.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImp implements IProductDetailService {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Override
    public ProductDetail addProductDetail(ProductDetailAddingRequest request) {
        Product product = productRepository.findById(request.getProductID())
                .orElseThrow(() -> new ProductIdNotFoundException(request.getProductID()));
        Color color = colorRepository.findById(request.getColorId())
                .orElseThrow(() -> new ColorIdNotFoundException(request.getColorId()));
        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new SizeIdNotFoundException(request.getSizeId()));
        ProductDetail newProductDetail = new ProductDetail(product, color, size, request.getQuantity(), request.getPrice());
        return productDetailRepository.save(newProductDetail);
    }

    @Override
    public ProductDetail editProductDetail(ProductDetailEditRequest request) {
        ProductDetail productDetail = productDetailRepository.findById(request.getId())
                .orElseThrow(() -> new ProductIdNotFoundException(request.getId()));
        Color color = colorRepository.findById(request.getColorId())
                .orElseThrow(() -> new ColorIdNotFoundException(request.getColorId()));
        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new SizeIdNotFoundException(request.getSizeId()));
        productDetail.setColor(color);
        productDetail.setSize(size);
        productDetail.setQuantity(request.getQuantity());
        productDetail.setPrice(request.getPrice());
        return productDetailRepository.save(productDetail);
    }

    @Override
    public List<ProductDetail> getDetailsOfProduct(Integer productID) {
        return productDetailRepository.findByProductId(productID);
    }

    @Override
    public List<ProductDetail> getByPriceGreaterThan(float min, Pageable pageable) {
        return productDetailRepository.findByPriceGreaterThanEqual(min, pageable);
    }

    @Override
    public List<ProductDetail> getByPriceLessThan(float max, Pageable pageable) {
        return productDetailRepository.findByPriceLessThanEqual(max, pageable);
    }

    @Override
    public List<ProductDetail> getByPriceRange(float min, float max, Pageable pageable) {
        return productDetailRepository.findByPriceBetween(min, max, pageable);
    }
}
