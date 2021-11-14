package nashtech.longtran.shoppingweb.converter;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.dto.*;
import nashtech.longtran.shoppingweb.entity.*;
import nashtech.longtran.shoppingweb.exception.BrandIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.response.CategoryResponse;
import nashtech.longtran.shoppingweb.repository.BrandRepository;
import nashtech.longtran.shoppingweb.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductDetailConverter productDetailConverter;

    public Product convertToEntity(ProductDTO dto){
        Product product  = modelMapper.map(dto, Product.class);
        Brand brand = brandRepository.getBrandById(dto.getBrandId())
                .orElseThrow(() -> new BrandIdNotFoundException(ErrorCode.ERR_BRAND_ID_NOT_FOUND));
        product.setBrand(brand);
        product.setCategory(modelMapper.map(dto , Category.class));
        product.setProductDetails(dto.getProductDetails()
                .stream()
                .map(productDetailConverter::convertToEntity)
                .collect(Collectors.toSet()));
        return product;
    }

    public ProductDTO convertToDTO(Product product){
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        Category pCate = categoryRepository.getById(product.getCategory().getParentID());
        productCategoryDTO.setParentID(pCate.getId());
        productCategoryDTO.setPCategoryName(pCate.getName());
        productCategoryDTO.setCategoryID(product.getCategory().getId());
        productCategoryDTO.setCategoryName(product.getCategory().getName());
        Set<String> size = new HashSet<>();
        Set<String> colors = new HashSet<>();
        dto.setCategory(productCategoryDTO);
        dto.setProductDetails(product.getProductDetails()
                .stream()
                .map(productDetail -> {
                    ProductDetailDTO pDetail =  productDetailConverter.convertToDTO(productDetail);
                    size.add(pDetail.getSize());
                    colors.add(pDetail.getColor());
                    return pDetail;
                })
                .collect(Collectors.toSet()));
        dto.setColors(colors);
        dto.setSizes(size);
        dto.setBrandId(product.getBrand().getId());
        dto.setBrandName(product.getBrand().getName());
        return dto;
    }
}
