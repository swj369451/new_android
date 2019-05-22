package com.sm130.application130.domain;

import java.io.Serializable;

public class Share implements Serializable {
    private int imageId;
    private int cImageId;
    private String biaoti;
    private Boolean choice;


    public Share(int imageId,int cImageId , String biaoti, Boolean choice) {
        this.imageId = imageId;
        this.cImageId = cImageId;
        this.biaoti = biaoti;
        this.choice = choice;
    }


    public int getcImageId() {
        return cImageId;
    }

    public void setcImageId(int cImageId) {
        this.cImageId = cImageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    public Boolean getChoice() {
        return choice;
    }

    public void setChoice(Boolean choice) {
        this.choice = choice;
    }
}
