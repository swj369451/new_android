/**
  * Copyright 2019 bejson.com 
  */
package com.sm130.application130.beijing_domain;
import java.io.Serializable;
import java.util.Date;

/**
 * Auto-generated: 2019-05-17 21:10:17
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class News implements Serializable {

    private boolean comment;
    private String commentlist;
    private String commenturl;
    private int id;
    private String listimage;
    private String pubdate;
    private String title;
    private String type;
    private String url;
    public void setComment(boolean comment) {
         this.comment = comment;
     }
     public boolean getComment() {
         return comment;
     }

    public void setCommentlist(String commentlist) {
         this.commentlist = commentlist;
     }
     public String getCommentlist() {
         return commentlist;
     }

    public boolean isComment() {
        return comment;
    }

    public String getCommenturl() {
        return commenturl;
    }

    public void setCommenturl(String commenturl) {
        this.commenturl = commenturl;
    }

    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setListimage(String listimage) {
         this.listimage = listimage;
     }
     public String getListimage() {
         return listimage;
     }

    public void setPubdate(String pubdate) {
         this.pubdate = pubdate;
     }
     public String getPubdate() {
         return pubdate;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

}