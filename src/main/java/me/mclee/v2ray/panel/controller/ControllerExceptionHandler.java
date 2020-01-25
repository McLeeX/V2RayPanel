package me.mclee.v2ray.panel.controller;

import me.mclee.v2ray.panel.common.AppException;
import me.mclee.v2ray.panel.common.ErrorCode;
import me.mclee.v2ray.panel.common.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Serializable;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ResponseData<Serializable>> appExceptionHandler(AppException exception) {
        int errorCode = exception.getErrorCode();
        String message = exception.getMessage();
        ResponseData<Serializable> res = new ResponseData<>();
        res.setErrorCode(errorCode);
        res.setMessage(message);
        HttpStatus httpStatus;
        if (errorCode == ErrorCode.INTERNAL_ERROR) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(res, httpStatus);
    }
}
