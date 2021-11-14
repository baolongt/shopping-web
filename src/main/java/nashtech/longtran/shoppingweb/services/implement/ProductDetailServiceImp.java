package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.constant.SuccessCode;
import nashtech.longtran.shoppingweb.dto.ProductDetailDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.ProductDetail;
import nashtech.longtran.shoppingweb.exception.ProductIdNotFoundException;
import nashtech.longtran.shoppingweb.repository.ProductDetailRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.services.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailServiceImp implements IProductDetailService {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseDTO addProductDetail(ProductDetailDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        Product product = productRepository.findById(request.getProductID())
                .orElseThrow(() -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));
        ProductDetail newProductDetail = new ProductDetail(product, request.getColor(), request.getSize(),
                request.getQuantity(), request.getPrice(), request.getImgURL());
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
        try {
            productDetail.setColor(request.getColor());
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
    public ResponseDTO getDetailsOfProduct(Integer productID) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(productDetailRepository.findByProductId(productID));
        return responseDTO;

    }

    @Override
    public ResponseDTO getByPriceGreaterThan(float min, Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(productDetailRepository.findByPriceGreaterThanEqual(min, pageable));
        return responseDTO;
    }

    @Override
    public ResponseDTO  getByPriceLessThan(float max, Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(productDetailRepository.findByPriceLessThanEqual(max, pageable));
        return responseDTO;
    }

    @Override
    public ResponseDTO  getByPriceRange(float min, float max, Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(productDetailRepository.findByPriceBetween(min, max, pageable));
        return responseDTO;
    }
}
