/**
  * Copyright 2019 bejson.com 
  */
package com.sm130.application130.zutu_domain;
import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2019-05-18 19:34:5
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data implements Serializable {

    private String countcommenturl;
    private String more;
    private List<News> news;
    private String title;
    private List<String> topic;
    public void setCountcommenturl(String countcommenturl) {
         this.countcommenturl = countcommenturl;
     }
     public String getCountcommenturl() {
         return countcommenturl;
     }

    public void setMore(String more) {
         this.more = more;
     }
     public String getMore() {
         return more;
     }

    public void setNews(List<News> news) {
         this.news = news;
     }
     public List<News> getNews() {
         return news;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setTopic(List<String> topic) {
         this.topic = topic;
     }
     public List<String> getTopic() {
         return topic;
     }

}