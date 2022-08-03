package com.zistrong.javaee8.ws;

import com.zistrong.javaee8.service.MailService;
import com.zistrong.javaee8.service.TimeService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Named
@WebService(targetNamespace = "WS")
public class WsAPI {

    @Inject
    TimeService timeService;

    @Inject
    MailService mailService;

    @WebMethod
    public String now(@WebParam(name = "i") int i) {
        mailService.testMail();
        return i + ":" + timeService.getTime();
    }
}
