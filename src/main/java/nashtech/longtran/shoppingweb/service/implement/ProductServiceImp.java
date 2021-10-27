package nashtech.longtran.shoppingweb.service.implement;

import nashtech.longtran.shoppingweb.entity.Category;
import nashtech.longtran.shoppingweb.entity.Product;
import nashtech.longtran.shoppingweb.exception.CategoryIdNotFoundException;
import nashtech.longtran.shoppingweb.exception.ProductIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.ProductAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ProductEditRequest;
import nashtech.longtran.shoppingweb.repository.CategoryRepository;
import nashtech.longtran.shoppingweb.repository.ProductRepository;
import nashtech.longtran.shoppingweb.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ProductServiceImp implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    public Product addProduct(ProductAddingRequest request) {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        Product newProduct = new Product(request.getProductName(), request.getPrice(),
                request.getQuantity(), request.getBrand(),request.getDetail(),  current ,current);
        return productRepository.save(newProduct);
    }

    @Override
    public Product editProduct(ProductEditRequest request) {
        Product product =  productRepository.findById(request.getId())
                .orElseThrow(() -> new ProductIdNotFoundException(request.getId()));
        product.setName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setBrand(request.getBrand());
        product.setQuantity(request.getQuantity());
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
    public List<Product> getByCategory(int categoryID, Pageable pageable) {
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(() ->new CategoryIdNotFoundException(categoryID));
        return productRepository.findByCategory(category,pageable);
    }

    @Override
    public List<Product> getByPriceGreaterThan(float min, Pageable pageable) {
        return productRepository.findByPriceGreaterThanEqual(min, pageable);
    }

    @Override
    public List<Product> getByPriceLessThan(float max, Pageable pageable) {
        return productRepository.findByPriceLessThanEqual(max, pageable);
    }

    @Override
    public List<Product> getByPriceRange(float min, float max, Pageable pageable) {
        return productRepository.findByPriceBetween(min, max, pageable);
    }
}
