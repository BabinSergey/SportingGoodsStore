package com.babin.sportinggoodsstore.service;

import com.babin.sportinggoodsstore.ws.greeting.Greeting;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;

@Component
public class GreetingService {
    public Greeting generateGreeting(String name) throws DatatypeConfigurationException {
        Greeting greeting = new Greeting();
        greeting.setText("Привет, " + name);
        greeting.setDate(DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(new GregorianCalendar()));
        return greeting;
    }
}
