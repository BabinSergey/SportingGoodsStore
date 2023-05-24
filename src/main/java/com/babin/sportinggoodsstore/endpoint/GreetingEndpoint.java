package com.babin.sportinggoodsstore.endpoint;

import com.babin.sportinggoodsstore.service.GreetingService;
import com.babin.sportinggoodsstore.ws.greeting.GetGreetingRequest;
import com.babin.sportinggoodsstore.ws.greeting.GetGreetingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class GreetingEndpoint {

    public static final String NAMESPACE_URL = "http://babin.com/sportinggoodsstore/ws/greeting";

    private GreetingService greetingService;

    @Autowired
    public GreetingEndpoint(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PayloadRoot(namespace = NAMESPACE_URL, localPart = "getGreetingRequest")
    @ResponsePayload
    public GetGreetingResponse getGreeting(@RequestPayload GetGreetingRequest request)
            throws DatatypeConfigurationException {
        GetGreetingResponse response = new GetGreetingResponse();

        response.setGreeting(greetingService.generateGreeting(request.getName()));

        return response;
    }
}
