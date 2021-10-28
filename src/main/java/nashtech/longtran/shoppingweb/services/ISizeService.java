package nashtech.longtran.shoppingweb.services;

import nashtech.longtran.shoppingweb.entity.Size;
import nashtech.longtran.shoppingweb.payload.request.SizeAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.SizeEditRequest;

public interface ISizeService {
    Size addNewSize(SizeAddingRequest request);
    Size editSize(SizeEditRequest request);
}
