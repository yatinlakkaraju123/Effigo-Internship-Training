package com.in28minutes.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public Person getFirstVersionOfPerson(){
        return new Person("Bob");
    }
    @GetMapping("/v2/person")
    public Personv2 getSecondVersionOfPerson(){
        return new Personv2("Bob","Charlie");
    }

    @GetMapping(path="person",params = "version=1")
    public Person getFirstVersionOfPersonUsingParams(){
        return new Person("Bob");
    }
    @GetMapping(path="person/header",headers = "X-API-VERSION=1")
    public Person getFirstVersionOfPersonUsingRequestHeaders(){
        return new Person("Bob");
    }
    /*@GetMapping(path="person/accept",produces = "application/")
    public Person getFirstVersionOfPersonUsingProduces(){
        return new Person("Bob");
    }*/
}
