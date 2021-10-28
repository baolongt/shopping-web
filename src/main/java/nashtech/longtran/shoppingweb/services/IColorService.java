package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.entity.Color;
import nashtech.longtran.shoppingweb.payload.request.ColorAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ColorEditRequest;

public interface IColorService {
    Color addNewColor(String name,  String colorHex);
    Color editColor(Integer id, String name, String colorHex);
}
