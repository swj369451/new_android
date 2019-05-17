/**
  * Copyright 2019 bejson.com 
  */
package com.sm130.application130.qiche_domain;
import java.util.List;

/**
 * Auto-generated: 2019-05-17 21:2:38
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private String title;
    private List<Topnews> topnews;
    private List<Topic> topic;
    private List<News> news;
    private String countcommenturl;
    private String more;
    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setTopnews(List<Topnews> topnews) {
         this.topnews = topnews;
     }
     public List<Topnews> getTopnews() {
         return topnews;
     }

    public void setTopic(List<Topic> topic) {
         this.topic = topic;
     }
     public List<Topic> getTopic() {
         return topic;
     }

    public void setNews(List<News> news) {
         this.news = news;
     }
     public List<News> getNews() {
         return news;
     }

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

}