package com.productcategory.crud.entity;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class MyBean {

    public MyBean() {
        System.out.println("Bean is instantiated.");
    }

    @PostConstruct
    public void init() {
        System.out.println("Bean is initialized.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean is about to be destroyed.");
    }
}
