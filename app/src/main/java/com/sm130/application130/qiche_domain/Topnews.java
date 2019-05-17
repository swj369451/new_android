/**
  * Copyright 2019 bejson.com 
  */
package com.sm130.application130.qiche_domain;
import java.util.Date;

/**
 * Auto-generated: 2019-05-17 21:2:38
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Topnews {

    private int id;
    private String title;
    private String topimage;
    private String url;
    private Date pubdate;
    private boolean comment;
    private Date commenturl;
    private String type;
    private String commentlist;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setTopimage(String topimage) {
         this.topimage = topimage;
     }
     public String getTopimage() {
         return topimage;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    public void setPubdate(Date pubdate) {
         this.pubdate = pubdate;
     }
     public Date getPubdate() {
         return pubdate;
     }

    public void setComment(boolean comment) {
         this.comment = comment;
     }
     public boolean getComment() {
         return comment;
     }

    public void setCommenturl(Date commenturl) {
         this.commenturl = commenturl;
     }
     public Date getCommenturl() {
         return commenturl;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setCommentlist(String commentlist) {
         this.commentlist = commentlist;
     }
     public String getCommentlist() {
         return commentlist;
     }

}