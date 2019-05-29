package com.sm130.application130.domain;

import java.io.Serializable;

public class Interface implements Serializable {
    String title;
    Boolean ct;
    String content;

    public Interface(String title, Boolean ct, String content) {
        this.title = title;
        this.ct = ct;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCt() {
        return ct;
    }

    public void setCt(Boolean ct) {
        this.ct = ct;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
