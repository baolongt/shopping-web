package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.constant.SuccessCode;
import nashtech.longtran.shoppingweb.dto.ProductDetailDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Color;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.ProductDetail;
import nashtech.longtran.shoppingweb.entity.Size;
import nashtech.longtran.shoppingweb.exception.ColorIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.ProductDetailIdNotFoundException;
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

    @Override
    public ResponseDTO addProductDetail(ProductDetailDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        Product product = productRepository.findById(request.getProductID())
                .orElseThrow(() -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));
        Color color = colorRepository.findById(request.getColorId())
                .orElseThrow(() -> new ColorIdNotFoundException(ErrorCode.ERR_COLOR_ID_NOT_FOUND));
        ProductDetail newProductDetail = new ProductDetail(product, color, request.getSize(), request.getQuantity(), request.getPrice());
        try{
            productDetailRepository.save(newProductDetail);
            responseDTO.setSuccessCode(SuccessCode.ADD_PRODUCT_DETAIL_SUCCESS);
        }
        catch (Exception  e){
            e.printStackTrace();
            responseDTO.setErrorCode(ErrorCode.ERR_SAVE_PRODUCT_DETAIL);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO editProductDetail(ProductDetailDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        ProductDetail productDetail = productDetailRepository.findById(request.getId())
                .orElseThrow(() -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));
        Color color = colorRepository.findById(request.getColorId())
                .orElseThrow(() -> new ColorIdNotFoundException(ErrorCode.ERR_COLOR_ID_NOT_FOUND));
        try {
            productDetail.setColor(color);
            productDetail.setSize(request.getSize());
            productDetail.setQuantity(request.getQuantity());
            productDetail.setPrice(request.getPrice());
            productDetailRepository.save(productDetail);
        }
        catch (Exception e){
            e.printStackTrace();
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_DETAIL);
        }
        return responseDTO;
    }

    @Override
    public ProductDetail getDetailsOfProduct(Integer productID) {
        return productDetailRepository.findByProductId(productID)
                .orElseThrow(() -> new ProductDetailIdNotFoundException(ErrorCode.ERR_PRODUCT_DETAIL_ID_NOT_FOUND));
    }

    @Override
    public List<ProductDetail>  getByPriceGreaterThan(float min, Pageable pageable) {
        return productDetailRepository.findByPriceGreaterThanEqual(min, pageable);
    }

    @Override
    public List<ProductDetail>  getByPriceLessThan(float max, Pageable pageable) {
        return productDetailRepository.findByPriceLessThanEqual(max, pageable);
    }

    @Override
    public List<ProductDetail>  getByPriceRange(float min, float max, Pageable pageable) {
        return productDetailRepository.findByPriceBetween(min, max, pageable);
    }
}
