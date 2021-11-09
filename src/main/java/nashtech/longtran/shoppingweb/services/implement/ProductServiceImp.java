package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.constant.SuccessCode;
import nashtech.longtran.shoppingweb.converter.ProductConverter;
import nashtech.longtran.shoppingweb.converter.ProductDetailConverter;
import nashtech.longtran.shoppingweb.dto.ProductDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Brand;
import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.exception.BrandIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.CategoryIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.ProductIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.ProductAddingRequest;
import nashtech.longtran.shoppingweb.repository.BrandRepository;
import nashtech.longtran.shoppingweb.repository.CategoryRepository;
import nashtech.longtran.shoppingweb.repository.ProductDetailRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.services.IProductService;
import nashtech.longtran.shoppingweb.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements IProductService {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ProductConverter productConverter;

    @Autowired
    ProductDetailConverter productDetailConverter;

    @Autowired
    ProductSpecification productSpecification;

    @Override
    public ResponseDTO getAll(Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(productRepository.findAll(pageable)
                .map(product -> productConverter.convertToDTO(product)));
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_PRODUCT_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO addProduct(ProductAddingRequest request) throws BrandIdNotFoundException{
        ResponseDTO responseDTO = new ResponseDTO();
        Timestamp current = new Timestamp(System.currentTimeMillis());
        Brand brand = brandRepository.getBrandById(request.getBrandID())
                .orElseThrow(()->new BrandIdNotFoundException(ErrorCode.ERR_BRAND_ID_NOT_FOUND));
        try {
            Product newProduct = new Product(request.getName(), brand, request.getDetail(), current, current);
            Set<Category> categoryList = new HashSet<>(categoryRepository
                    .findAllById(request.getCategories()));
            newProduct.setCategories(categoryList);
            productRepository.saveAndFlush(newProduct);
            responseDTO.setSuccessCode(SuccessCode.ADD_PRODUCT_SUCCESS);
        }
        catch (Exception e){
            responseDTO.setErrorCode(ErrorCode.ERR_SAVE_PRODUCT);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO editProduct(ProductDTO request) throws BrandIdNotFoundException{
        ResponseDTO responseDTO = new ResponseDTO();
        Product product =  productRepository.findById(request.getId())
                .orElseThrow(() -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));
        Brand brand = brandRepository.getBrandById(request.getBrand().getId())
                .orElseThrow(()->new BrandIdNotFoundException(ErrorCode.ERR_BRAND_ID_NOT_FOUND));
        try {
            product.setName(request.getName());
            product.setBrand(brand);
            product.setDetail(request.getDetail());
            product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            productRepository.save(product);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_PRODUCT_SUCCESS);
        }
        catch (Exception e){
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_CATEGORY);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getByID(int productID) {
        ResponseDTO responseDTO = new ResponseDTO();
        Product product = productRepository.findProductById(productID)
                .orElseThrow(() -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));
        responseDTO.setData(productConverter.convertToDTO(product));
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_PRODUCT_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO findByName(String name, Pageable pageable) {
        if(name.trim().isEmpty()){
            return getAll(pageable);
        }
        ResponseDTO responseDTO = new ResponseDTO();
        Page<Product> productList =  productRepository.findAllByNameContaining(name, pageable);
        productList.stream().forEach(product -> productConverter.convertToDTO(product)); ;
        responseDTO.setData(productList);
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_PRODUCT_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO getByCategory(String categoryName, Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
            Category category = categoryRepository.findByName(categoryName)
                    .orElseThrow(() ->new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_NAME_NOT_FOUND));
        responseDTO.setData(productRepository.findByCategories_Id(category.getId(),pageable));
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_PRODUCT_SUCCESS);
        return responseDTO;
    }

}
