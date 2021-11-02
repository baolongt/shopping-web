package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.BrandDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Brand;
import nashtech.longtran.shoppingweb.payload.request.BrandAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.BrandEditRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBrandService {
    ResponseDTO getAll(Pageable pageable);
    ResponseDTO addNewBrand(BrandDTO request);
    ResponseDTO editBrand(BrandDTO request);
}
