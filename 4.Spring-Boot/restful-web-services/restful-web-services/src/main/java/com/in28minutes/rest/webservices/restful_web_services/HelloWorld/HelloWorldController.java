package com.in28minutes.rest.webservices.restful_web_services.HelloWorld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "hello world is a very good page";
    }
    @GetMapping(path = "/hello-world-internationalization")
    public String helloWorldInternationalization(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
       // return "hello world v2";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloBean helloWorldBean(){
        return new HelloBean("Hello world bean");
    }
    @GetMapping(path = "/hello-world/{name}")
    public HelloBean helloWorldPathVariable(@PathVariable String name){
        return new HelloBean(String.format("Hello World, %s",name));
    }
}
