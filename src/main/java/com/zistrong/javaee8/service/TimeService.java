package com.zistrong.javaee8.service;

import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class TimeService {

    public String getTime() {
       return new Date().toInstant().toString();
    }
}
