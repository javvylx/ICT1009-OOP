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

public class news{
    private String publishedAt;
    private String author, urlToImage; 
    private String[] source;
    private String description, title, url, content;
    
    public news(String publishedAt, String author, String urlToImage, String description, String title, String url, String content) {
        this.publishedAt = publishedAt;
        this.author = author;
        this.urlToImage = urlToImage;
        this.description = description;
        this.title = title;
        this.url = url;
        this.content = content;
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
}

