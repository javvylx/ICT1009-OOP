/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;

import java.util.*;

/**
 *
 * @author chinb
 */
public class news {

    private String publishedAt, author, urlToImage, description, title, url, content, sourceID, sourceName;

    public news(String publishedAt, String author, String urlToImage, String description, String title, String url, String content, String sourceID, String sourceName) {
        this.publishedAt = publishedAt;
        this.author = author;
        this.urlToImage = urlToImage;
        this.description = description;
        this.title = title;
        this.url = url;
        this.content = content;
        this.sourceID = sourceID;
        this.sourceName = sourceName;
    }

    public String getpublishedAt() {
        return publishedAt;
    }

    public String getauthor() {
        return author;
    }

    public String geturlToImage() {
        return urlToImage;
    }

    public String getdescription() {
        return description;
    }

    public String gettitle() {
        return title;
    }

    public String geturl() {
        return url;
    }

    public String getcontent() {
        return content;
    }

    public String getsourceID() {
        return sourceID;
    }

    public String getsourceName() {
        return sourceName;
    }
    
    @Override
    public String toString(){
        return "Published at: " + publishedAt
                + "\nAuthor: " + author
                + "\nUrl To Image:  " + urlToImage
                + "\nDescription " + description
                + "\nTitle: " +  title
                + "\nUrl: " + url
                + "\nContent: " + content
                + "\nSource ID: " + sourceID
                + "\nSource Name: " + sourceName;
    }
}
