package com.saraf.soap.config;

import com.saraf.soap.ems.GetEmployeeRequest;
import com.saraf.soap.ems.GetEmployeeResponse;
import com.saraf.soap.ems.ObjectFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;

@Endpoint
public class EndpointConfig {

    @PayloadRoot(namespace = "http://soap.saraf.com/EMS", localPart = "GetEmployeeRequest")
   @ResponsePayload
    public JAXBElement<GetEmployeeResponse> getEmployeeData(@RequestPayload GetEmployeeRequest request){
        GetEmployeeResponse getEmployeeResponse = new GetEmployeeResponse();
        getEmployeeResponse.setEmployeeId(request.getEmployeeId());
        getEmployeeResponse.setEmployeeName("Rohit");
        getEmployeeResponse.setLocation("Bangalore");
        getEmployeeResponse.setZipCode(560067);
        return new ObjectFactory().createGetEmployeeResponse(getEmployeeResponse);
    }
}
