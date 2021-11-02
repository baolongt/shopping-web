package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.constant.SuccessCode;
import nashtech.longtran.shoppingweb.dto.ProductDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Brand;
import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.entity.ProductDetail;
import nashtech.longtran.shoppingweb.exception.BrandIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.CategoryIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.ProductIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.ProductAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ProductEditRequest;
import nashtech.longtran.shoppingweb.repository.BrandRepository;
import nashtech.longtran.shoppingweb.repository.CategoryRepository;
import nashtech.longtran.shoppingweb.repository.ProductDetailRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.services.IProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public ResponseDTO getAll(Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(productRepository.findAll(pageable).getContent());
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_PRODUCT_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO addProduct(ProductDTO request) throws BrandIdNotFoundException{
        ResponseDTO responseDTO = new ResponseDTO();
        Timestamp current = new Timestamp(System.currentTimeMillis());
        Brand brand = brandRepository.getBrandById(request.getBrandId())
                .orElseThrow(()->new BrandIdNotFoundException(ErrorCode.ERR_BRAND_ID_NOT_FOUND));
        try {
            Product newProduct = new Product(request.getProductName(), brand, request.getDetail(), current, current);
            productRepository.saveAndFlush(newProduct);
            responseDTO.setSuccessCode(SuccessCode.ADD_PRODUCT_SUCCESS);
            request.getProductDetails().forEach(productDetailDTO -> {

            });
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
        Brand brand = brandRepository.getBrandById(request.getBrandId())
                .orElseThrow(()->new BrandIdNotFoundException(ErrorCode.ERR_BRAND_ID_NOT_FOUND));
        try {
            product.setName(request.getProductName());
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
        Product product = productRepository.findById(productID)
                .orElseThrow(() -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));
        responseDTO.setData(product);
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_PRODUCT_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO findByName(String name, Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData( productRepository.findAllByNameContaining(name, pageable));
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_PRODUCT_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO getByCategories(int[] categoriesID, Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        Set<Category> categories  = new HashSet<>();
        for (int categoryID : categoriesID) {
            Category category = categoryRepository.findById(categoryID)
                    .orElseThrow(() ->new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND));
            categories.add(category);
        }
        responseDTO.setData(productRepository.findByCategoriesIn(categories,pageable));
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_PRODUCT_SUCCESS);
        return responseDTO;
    }

}
