package nashtech.longtran.shoppingweb.converter;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.dto.BrandDTO;
import nashtech.longtran.shoppingweb.dto.CategoryDTO;
import nashtech.longtran.shoppingweb.dto.ProductDTO;
import nashtech.longtran.shoppingweb.entity.*;
import nashtech.longtran.shoppingweb.exception.BrandIdNotFoundException;
import nashtech.longtran.shoppingweb.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductConverter {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ProductDetailConverter productDetailConverter;

    public Product convertToEntity(ProductDTO dto){
        Product product  = modelMapper.map(dto, Product.class);
        Brand brand = brandRepository.getBrandById(dto.getBrand().getId())
                .orElseThrow(() -> new BrandIdNotFoundException(ErrorCode.ERR_BRAND_ID_NOT_FOUND));
        product.setBrand(brand);
        product.setCategories(dto.getCategories()
                .stream()
                .map(categoryDTO -> modelMapper.map(categoryDTO, Category.class))
                .collect(Collectors.toSet()));
        product.setProductDetails(dto.getProductDetails()
                .stream()
                .map(productDetailConverter::convertToEntity)
                .collect(Collectors.toSet()));
        return product;
    }

    public ProductDTO convertToDTO(Product product){
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        dto.setCategories(product.getCategories()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toSet()));
        dto.setProductDetails(product.getProductDetails()
                .stream()
                .map(productDetail -> productDetailConverter.convertToDTO(productDetail))
                .collect(Collectors.toSet()));
        dto.setBrand(modelMapper.map(product.getBrand(), BrandDTO.class));
        return dto;
    }
}
