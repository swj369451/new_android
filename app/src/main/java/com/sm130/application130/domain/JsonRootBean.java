/**
  * Copyright 2019 bejson.com 
  */
package com.sm130.application130.domain;
import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2019-05-15 16:47:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean implements Serializable {

    private int retcode;
    private List<Data> data;
    private List<Integer> extend;
    public void setRetcode(int retcode) {
         this.retcode = retcode;
     }
     public int getRetcode() {
         return retcode;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setExtend(List<Integer> extend) {
         this.extend = extend;
     }
     public List<Integer> getExtend() {
         return extend;
     }

}