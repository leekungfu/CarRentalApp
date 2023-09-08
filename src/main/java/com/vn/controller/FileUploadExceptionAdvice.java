package com.vn.controller;

import com.vn.dto.MessageResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<MessageResult> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new MessageResult(false,"One or more files are too large!", null, null));
    }
}
