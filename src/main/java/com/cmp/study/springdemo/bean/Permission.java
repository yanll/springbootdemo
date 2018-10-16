package com.cmp.study.springdemo.bean;

public class Permission {
    private Long id;
    private String name;
    private String url;
    private String method;

    public Permission() {
    }

    public Permission(Long id, String name, String url, String method) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.method = method;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
