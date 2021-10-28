package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.entity.Brand;
import nashtech.longtran.shoppingweb.exception.BrandIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.BrandAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.BrandEditRequest;
import nashtech.longtran.shoppingweb.repository.BrandRepository;
import nashtech.longtran.shoppingweb.services.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImp implements IBrandService {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public Brand addNewBrand(String name) {
        Brand newBrand = new Brand(name);
        return brandRepository.save(newBrand);
    }

    @Override
    public Brand editBrand(Integer id, String name) {
        Brand brand = brandRepository.getBrandById(id)
                .orElseThrow(() -> new BrandIdNotFoundException(id));
        brand.setName(name);
        return brandRepository.save(brand);
    }
}
