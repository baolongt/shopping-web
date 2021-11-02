package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.constant.SuccessCode;
import nashtech.longtran.shoppingweb.dto.BrandDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Brand;
import nashtech.longtran.shoppingweb.exception.BrandIdNotFoundException;
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
    public ResponseDTO getAll(Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Brand> brandList = brandRepository.findAll(pageable).getContent();

        responseDTO.setData(brandList);
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_BRAND_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO addNewBrand(BrandDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            brandRepository.save(new Brand(request.getName()));
            responseDTO.setSuccessCode(SuccessCode.ADD_BRAND_SUCCESS);
        }
        catch (Exception e){
            responseDTO.setErrorCode(ErrorCode.ERR_SAVE_BRAND);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO editBrand(BrandDTO request){
        ResponseDTO responseDTO = new ResponseDTO();
        Brand brand = brandRepository.getBrandById(request.getId())
                .orElseThrow(() -> new BrandIdNotFoundException(ErrorCode.ERR_BRAND_ID_NOT_FOUND));
        try {
            brand.setName(request.getName());
            responseDTO.setSuccessCode(SuccessCode.UPDATE_BRAND_SUCCESS);
        }
        catch (Exception  e){
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_BRAND);
        }
        return responseDTO;
    }
}
