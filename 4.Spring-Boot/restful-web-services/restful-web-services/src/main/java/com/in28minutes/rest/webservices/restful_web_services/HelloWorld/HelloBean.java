package com.in28minutes.rest.webservices.restful_web_services.HelloWorld;

public class HelloBean {
    private String  message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloBean{" +
                "message='" + message + '\'' +
                '}';
    }

    public HelloBean(String message) {
        this.message = message;
    }
}
