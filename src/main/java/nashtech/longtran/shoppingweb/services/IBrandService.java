package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.BrandDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IBrandService {
    ResponseDTO getAll();
    ResponseDTO addNewBrand(BrandDTO request);
    ResponseDTO editBrand(BrandDTO request);
}
