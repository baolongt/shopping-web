package nashtech.longtran.shoppingweb.converter;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.dto.ColorDTO;
import nashtech.longtran.shoppingweb.dto.ProductDTO;
import nashtech.longtran.shoppingweb.dto.ProductDetailDTO;
import nashtech.longtran.shoppingweb.entity.Brand;
import nashtech.longtran.shoppingweb.entity.Color;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.ProductDetail;
import nashtech.longtran.shoppingweb.exception.BrandIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.ColorIdNotFoundException;
import nashtech.longtran.shoppingweb.repository.BrandRepository;
import nashtech.longtran.shoppingweb.repository.ColorRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailConverter {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

    public ProductDetail convertToEntity(ProductDetailDTO dto){
        ProductDetail productDetail = modelMapper.map(dto, ProductDetail.class);
        Color color = colorRepository.findById(dto.getColor().getId())
                .orElseThrow(() -> new ColorIdNotFoundException(ErrorCode.ERR_COLOR_ID_NOT_FOUND));
        productDetail.setColor(color);
        return productDetail;
    }

    public ProductDetailDTO  convertToDTO(ProductDetail productDetail){
        ProductDetailDTO dto = modelMapper.map(productDetail, ProductDetailDTO.class);
        Color color = colorRepository.findById(productDetail.getColor().getId())
                .orElseThrow(() -> new ColorIdNotFoundException(ErrorCode.ERR_COLOR_ID_NOT_FOUND));
        dto.setColor(modelMapper.map(color, ColorDTO.class));
        dto.setProductID(productDetail.getProduct().getId());
        return dto;
    }
}
