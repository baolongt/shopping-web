package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.entity.Brand;
import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.entity.Product;
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
    public List<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    public Product addProduct(ProductAddingRequest request) {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        Brand brand = brandRepository.getBrandById(request.getBrandId())
                .orElseThrow(()->new BrandIdNotFoundException(request.getBrandId()));
        Product newProduct = new Product(request.getProductName(), brand,request.getDetail(),  current ,current);
        return productRepository.save(newProduct);
    }

    @Override
    public Product editProduct(ProductEditRequest request) {
        Product product =  productRepository.findById(request.getId())
                .orElseThrow(() -> new ProductIdNotFoundException(request.getId()));
        Brand brand = brandRepository.getBrandById(request.getBrandId())
                .orElseThrow(()->new BrandIdNotFoundException(request.getBrandId()));
        product.setName(request.getProductName());
        product.setBrand(brand);
        product.setDetail(request.getDetail());
        product.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        return productRepository.save(product);
    }

    @Override
    public Product getByID(int productID) {
        return productRepository.findById(productID)
                .orElseThrow(() -> new ProductIdNotFoundException(productID));
    }

    @Override
    public List<Product> findByName(String name, Pageable pageable) {
        return productRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public List<Product> getByCategories(int[] categoriesID, Pageable pageable) {
        Set<Category> categories  = new HashSet<>();
        for (int categoryID : categoriesID) {
            Category category = categoryRepository.findById(categoryID)
                    .orElseThrow(() ->new CategoryIdNotFoundException(categoryID));
            categories.add(category);
        }
        return productRepository.findByCategoriesIn(categories,pageable);
    }

}
