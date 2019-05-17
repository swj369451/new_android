package com.sm130.application130;

import android.support.v4.app.FragmentManager;

import java.io.Serializable;

public class MyFragmentManager implements Serializable {

    FragmentManager supportFragmentManager;

    public MyFragmentManager(FragmentManager supportFragmentManager) {
        this.supportFragmentManager=supportFragmentManager;
    }

    public FragmentManager getSupportFragmentManager() {
        return supportFragmentManager;
    }

    public void setSupportFragmentManager(FragmentManager supportFragmentManager) {
        this.supportFragmentManager = supportFragmentManager;
    }
}
