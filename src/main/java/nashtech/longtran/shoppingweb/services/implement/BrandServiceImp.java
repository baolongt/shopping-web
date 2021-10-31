package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.entity.Brand;
import nashtech.longtran.shoppingweb.exception.BrandIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.BrandAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.BrandEditRequest;
import nashtech.longtran.shoppingweb.repository.BrandRepository;
import nashtech.longtran.shoppingweb.services.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImp implements IBrandService {

    @Autowired
    BrandRepository brandRepository;


    @Override
    public List<Brand> getAll(Pageable pageable) {
        return brandRepository.findAll(pageable).getContent();
    }

    @Override
    public Brand addNewBrand(BrandAddingRequest request) {
        Brand newBrand = new Brand(request.getName());
        return brandRepository.save(newBrand);
    }

    @Override
    public Brand editBrand(BrandEditRequest  request) {
        Brand brand = brandRepository.getBrandById(request.getId())
                .orElseThrow(() -> new BrandIdNotFoundException(request.getId()));
        brand.setName(request.getName());
        return brandRepository.save(brand);
    }
}
