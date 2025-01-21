package com.in28minutes.rest.webservices.restful_web_services.versioning;

public class Personv2 {
    private String bob;
    private String charlie;

    public Personv2(String bob, String charlie) {
        this.bob = bob;
        this.charlie = charlie;
    }

    public String getBob() {
        return bob;
    }

    public void setBob(String bob) {
        this.bob = bob;
    }

    public String getCharlie() {
        return charlie;
    }

    public void setCharlie(String charlie) {
        this.charlie = charlie;
    }

    @Override
    public String toString() {
        return "Personv2{" +
                "bob='" + bob + '\'' +
                ", charlie='" + charlie + '\'' +
                '}';
    }
}
