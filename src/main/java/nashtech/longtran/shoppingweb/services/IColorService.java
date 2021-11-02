package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.ColorDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.entity.Color;
import nashtech.longtran.shoppingweb.payload.request.ColorAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ColorEditRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IColorService {
    ResponseDTO getAll(Pageable pageable);
    ResponseDTO addNewColor(ColorDTO request);
    ResponseDTO editColor(ColorDTO request);
}
