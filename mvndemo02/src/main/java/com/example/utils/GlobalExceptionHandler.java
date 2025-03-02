package com.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        logger.error("全局异常处理", e);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(500);
        apiResponse.setStatus(HttpStatus.getHttpStatusByCode(500));
        apiResponse.setMessage("服务器内部错误：" + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.getCode()).body(apiResponse);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ApiResponse> handleJsonProcessingException(JsonProcessingException e) {
        logger.error("JSON序列化失败", e);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(400);
        apiResponse.setStatus(HttpStatus.getHttpStatusByCode(400));
        apiResponse.setMessage("JSON序列化失败：" + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.getCode()).body(apiResponse);
    }
}

