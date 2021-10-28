package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.entity.Brand;
import nashtech.longtran.shoppingweb.payload.request.BrandAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.BrandEditRequest;

public interface IBrandService {
    Brand addNewBrand(String name);
    Brand editBrand(Integer id, String name);
}
