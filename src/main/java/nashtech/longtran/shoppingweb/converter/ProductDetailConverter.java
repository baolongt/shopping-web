package nashtech.longtran.shoppingweb.converter;


import nashtech.longtran.shoppingweb.dto.ProductDetailDTO;
import nashtech.longtran.shoppingweb.entity.ProductDetail;
import nashtech.longtran.shoppingweb.repository.BrandRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailConverter {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

    public ProductDetail convertToEntity(ProductDetailDTO dto){
        ProductDetail productDetail = modelMapper.map(dto, ProductDetail.class);
        return productDetail;
    }

    public ProductDetailDTO  convertToDTO(ProductDetail productDetail){
        ProductDetailDTO dto = modelMapper.map(productDetail, ProductDetailDTO.class);
        dto.setProductID(productDetail.getProduct().getId());
        return dto;
    }
}
