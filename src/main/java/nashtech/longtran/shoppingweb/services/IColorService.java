package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.dto.ColorDTO;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IColorService {
    ResponseDTO getAll(Pageable pageable);
    ResponseDTO addNewColor(ColorDTO request);
    ResponseDTO editColor(ColorDTO request);
}
