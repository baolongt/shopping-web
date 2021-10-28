package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.entity.Color;
import nashtech.longtran.shoppingweb.exception.ColorIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.ColorAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ColorEditRequest;
import nashtech.longtran.shoppingweb.repository.ColorRepository;
import nashtech.longtran.shoppingweb.services.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceImp implements IColorService {

    @Autowired
    ColorRepository colorRepository;

    @Override
    public Color addNewColor(String name, String  colorHex) {
        Color color = new Color(name, colorHex);
        return colorRepository.save(color);
    }

    @Override
    public Color editColor(Integer id, String name, String  colorHex) {
        Color color = colorRepository.getColorById(id)
                .orElseThrow(() -> new ColorIdNotFoundException(id));
        color.setName(name);
        color.setColorHex(colorHex);
        return colorRepository.save(color);
    }
}
