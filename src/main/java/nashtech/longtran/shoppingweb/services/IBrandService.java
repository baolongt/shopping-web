package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.entity.Brand;
import nashtech.longtran.shoppingweb.payload.request.BrandAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.BrandEditRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBrandService {
    List<Brand> getAll(Pageable pageable);
    Brand addNewBrand(BrandAddingRequest request);
    Brand editBrand(BrandEditRequest request);
}
