package com.sm130.application130.domain;

import java.io.Serializable;

public class Interface implements Serializable {
    String title;
    Boolean ct;

    public Interface(String title, Boolean ct) {
        this.title = title;
        this.ct = ct;
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
}
