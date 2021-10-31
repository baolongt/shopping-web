package nashtech.longtran.shoppingweb.services.implement;

import nashtech.longtran.shoppingweb.entity.Color;
import nashtech.longtran.shoppingweb.exception.ColorIdNotFoundException;
import nashtech.longtran.shoppingweb.payload.request.ColorAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ColorEditRequest;
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
    public List<Color> getAll(Pageable pageable) {
        return colorRepository.findAll(pageable).getContent();
    }

    @Override
    public Color addNewColor(ColorAddingRequest request) {
        Color color = new Color(request.getName(), request.getColorHex());
        return colorRepository.save(color);
    }

    @Override
    public Color editColor(ColorEditRequest request) {
        Color color = colorRepository.getColorById(request.getId())
                .orElseThrow(() -> new ColorIdNotFoundException(request.getId()));
        color.setName(request.getName());
        color.setColorHex(request.getColorHex());
        return colorRepository.save(color);
    }
}
