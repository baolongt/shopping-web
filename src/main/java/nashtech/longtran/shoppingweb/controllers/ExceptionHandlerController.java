package nashtech.longtran.shoppingweb.controllers;

import nashtech.longtran.shoppingweb.constant.Error;
import nashtech.longtran.shoppingweb.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EmptyCategoryNameException.class)
    public ResponseEntity<String> handleEmptyCategoryName(EmptyCategoryNameException e){
        return new ResponseEntity<>(Error.EMPTY_CATEGORY_NAME_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CategoryIdNotFoundException.class)
    public ResponseEntity<String> handleCategoryIdNotFound(CategoryIdNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderIdNotFoundException.class)
    public ResponseEntity<String> handleOrderIdNotFound(OrderIdNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductIdNotFoundException.class)
    public ResponseEntity<String> handleProductIdNotFound(ProductIdNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFound(UsernameNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
