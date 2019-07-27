package com.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProviderApp {

    public static void main(String[] args){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbo.xml");
        context.start();
        System.out.println("-----dubbo开启-----");
    }
}
