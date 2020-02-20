/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

/**
 * Java Object - news
 * 
 * Method created here - getpublishedAt, getauthor, geturlToImage,
 * getdescription, gettitle, geturl, getcontent, getsourceID getsourceName
 */
public class news {

	private String publishedAt, author, urlToImage, description, title, url, content, sourceID, sourceName, country;

	public news(String publishedAt, String author, String urlToImage, String description, String title, String url,
			String content, String sourceID, String sourceName, String country) {
		this.publishedAt = publishedAt;
		this.author = author;
		this.urlToImage = urlToImage;
		this.description = description;
		this.title = title;
		this.url = url;
		this.content = content;
		this.sourceID = sourceID;
		this.sourceName = sourceName;
		this.country = country;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Published at: " + publishedAt + "\nAuthor: " + author + "\nUrl To Image:  " + urlToImage
				+ "\nDescription " + description + "\nTitle: " + title + "\nUrl: " + url + "\nContent: " + content
				+ "\nSource ID: " + sourceID + "\nSource Name: " + sourceName + "\nCountry: " + country;
	}
}
