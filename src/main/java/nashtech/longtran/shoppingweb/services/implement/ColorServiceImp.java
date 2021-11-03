package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.constant.SuccessCode;
import nashtech.longtran.shoppingweb.dto.ColorDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Color;
import nashtech.longtran.shoppingweb.exception.ColorIdNotFoundException;
import nashtech.longtran.shoppingweb.repository.ColorRepository;
import nashtech.longtran.shoppingweb.services.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImp implements IColorService {

    @Autowired
    ColorRepository colorRepository;

    @Override
    public ResponseDTO getAll(Pageable pageable) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(colorRepository.findAll(pageable).getContent());
        responseDTO.setSuccessCode(SuccessCode.RETRIEVE_COLOR_SUCCESS);
        return responseDTO;
    }

    @Override
    public ResponseDTO addNewColor(ColorDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        Color color = new Color(request.getName(), request.getColorHex());
        try{
            colorRepository.save(color);
            responseDTO.setSuccessCode(SuccessCode.ADD_COLOR_SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            responseDTO.setErrorCode(ErrorCode.ERR_SAVE_COLOR);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO editColor(ColorDTO request) {
        ResponseDTO responseDTO = new ResponseDTO();
        Color color = colorRepository.getColorById(request.getId())
                .orElseThrow(() -> new ColorIdNotFoundException(ErrorCode.ERR_COLOR_ID_NOT_FOUND));
        try {
            color.setName(request.getName());
            color.setColorHex(request.getColorHex());
             colorRepository.save(color);
            responseDTO.setSuccessCode(SuccessCode.UPDATE_COLOR_SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_COLOR);
        }
        return responseDTO;
    }
}
