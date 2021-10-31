package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.entity.Color;
import nashtech.longtran.shoppingweb.payload.request.ColorAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ColorEditRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IColorService {
    List<Color> getAll(Pageable pageable);
    Color addNewColor(ColorAddingRequest request);
    Color editColor(ColorEditRequest request);
}
