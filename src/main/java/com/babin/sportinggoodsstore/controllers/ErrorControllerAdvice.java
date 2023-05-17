package com.babin.sportinggoodsstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice                                                                            // контроллер на все приложение
public class ErrorControllerAdvice {

    @ExceptionHandler(Exception.class)                                                        // ловим все ошибки
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)                                         // статус ошибки на сервере
    public String exception(Exception exception, Model model){
        String errorMessage = (exception != null ? exception.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}
