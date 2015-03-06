package net.kiel.web.controller.form;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

// @See http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html
public class RetailerForm {
    @Getter @Setter
    private String name;
    
    @DateTimeFormat(iso=ISO.DATE)
    @Getter @Setter
    private Date beginDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Getter @Setter
    private Date endDate;
    }
