package nashtech.longtran.shoppingweb.converter;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.dto.ProductDTO;
import nashtech.longtran.shoppingweb.dto.ProductDetailDTO;
import nashtech.longtran.shoppingweb.entity.Color;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.ProductDetail;
import nashtech.longtran.shoppingweb.exception.ColorIdNotFoundException;
import nashtech.longtran.shoppingweb.repository.ColorRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductDetailConverter {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    ProductRepository productRepository;

    public ProductDetail convertToEntity(ProductDetailDTO dto){
        ProductDetail productDetail = modelMapper.map(dto, ProductDetail.class);
        Color color = colorRepository.findById(dto.getColorId())
                .orElseThrow(() -> new ColorIdNotFoundException(ErrorCode.ERR_COLOR_ID_NOT_FOUND));
        Product product = productRepository.findById(dto.getProductID())
                .orElseThrow(() -> new ColorIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));
        productDetail.setProduct(product);
        productDetail.setColor(color);
        return productDetail;
    }

    public ProductDetailDTO  convertToDTO(ProductDetail productDetail){
        ProductDetailDTO dto = modelMapper.map(productDetail, ProductDetailDTO.class);
        return dto;
    }
}
