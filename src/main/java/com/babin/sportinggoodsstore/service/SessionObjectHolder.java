package com.babin.sportinggoodsstore.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

// данный бин работает в сессии, считает клики
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionObjectHolder {
    private long amountClicks = 0;

    public SessionObjectHolder() {
        System.out.println("Сессия объекта создана!");
    }

    public long getAmountClicks() {
        return amountClicks;
    }

    public void addClick(){
        amountClicks++;
    }
}
