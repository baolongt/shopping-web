package nashtech.longtran.shoppingweb.controllers;

import nashtech.longtran.shoppingweb.constant.Error;
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
    public ResponseEntity<String> handleNotFound(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyCategoryNameException.class)
    public ResponseEntity<String> handleEmptyCategoryName(EmptyCategoryNameException e){
        return new ResponseEntity<>(Error.EMPTY_CATEGORY_NAME_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
