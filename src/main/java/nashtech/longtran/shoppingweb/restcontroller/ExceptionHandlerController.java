package nashtech.longtran.shoppingweb.restcontroller;

import nashtech.longtran.shoppingweb.constant.ErrorCode;
import nashtech.longtran.shoppingweb.dto.ResponseDTO;
import nashtech.longtran.shoppingweb.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseBody
public class ExceptionHandlerController {
    @ExceptionHandler(value = {
            BrandIdNotFoundException.class,
            CategoryIdNotFoundException.class,
            OrderIdNotFoundException.class,
            ProductIdNotFoundException.class,
            UsernameNotFoundException.class,
            ColorIdNotFoundException.class,
            SizeIdNotFoundException.class,
            ProductDetailIdNotFoundException.class})
        public ResponseEntity<ResponseDTO> handleNotFound(Exception e){
        return new ResponseEntity<>(new ResponseDTO(e.getMessage(), null, null), HttpStatus.NOT_FOUND);
    }



}
