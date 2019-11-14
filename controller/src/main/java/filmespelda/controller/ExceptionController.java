package filmespelda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.awt.*;

@ControllerAdvice
public class ExceptionController {

     @ExceptionHandler(IllegalArgumentException.class)
     public void testIllegalArgument(Exception e){
         System.out.println(e.getMessage());
     }

     @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public String mediatype(Exception e){
        return "Use one of the followings: "+ MediaType.APPLICATION_JSON_VALUE;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String mewthod(Exception e){
        return "Method is incorrect";
    }
}
