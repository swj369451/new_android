/**
  * Copyright 2019 bejson.com 
  */
package com.sm130.application130.zutu_domain;

import java.io.Serializable;

/**
 * Auto-generated: 2019-05-18 19:34:5
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean implements Serializable {

    private Data data;
    private int retcode;
    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

    public void setRetcode(int retcode) {
         this.retcode = retcode;
     }
     public int getRetcode() {
         return retcode;
     }

}