/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 3 methods is created here - String convertToJaveObj, HttpResponse<JsonNode>
 * newsapi, HashMap createHashMap
 * 
 * String convertToJaveObj - A method to assist in converting JSONObject into
 * JavaObject
 * 
 * HttpResponse<JsonNode> newsapi - It crawls and extract information via this
 * newsapi and return as a JsonNode format
 * 
 * HashMap createHashMap - Linking all JavaObject together thus making it
 * convenient to call
 */
public class newsapiMethod {

	public static String urlToImage, publishedAt, author, description, title, url, content, sourceID, sourceName, temp;
	public static String pageSize = "100", cat = "top-headlines", q = "", country = "";

	/**
	 * Method is called to replaced all 'null' with '-'
	 * 
	 * @param a - JSONObject that is placed
	 * @param b - Check if string is null
	 * @return - Return the string back
	 */
	public static String convertToJaveObj(JSONObject a, String b) {
		if (!a.isNull(b)) {
			temp = a.getString(b);
		} else {
			temp = "-";
		}
		return temp;
	}

	/**
	 * Documentation Link: https://newsapi.org/docs/endpoints/top-headlines
	 * Method to crawl and extract information from 'newsapi.org'
	 * 
	 * @param q        - Keywords inputted by user
	 * @param cat      - Category is set as top headlines by default but it can be
	 *                 changed by user
	 * @param pageSize - pageSize is set to maximum(100) by default as that is the
	 *                 API limitation
	 * @return - Return JsonNode back to main
	 * @throws Exception - Will display error message if the crawling was
	 *                   unsuccessful
	 */
	public static HttpResponse<JsonNode> newsapi(String q, String cat, String pageSize, String country) throws Exception {
		HttpResponse<JsonNode> response = Unirest.get(
				"https://newsapi.org/v2/{cat}?country={country}&category=business&keywords={keywords}&pageSize={pageSize}&apiKey={APIkey}")
				.routeParam("cat", cat)
				.routeParam("country", country)
				.routeParam("keywords", q)
				.routeParam("pageSize", pageSize)
				.routeParam("APIkey", "1e86e5f07d6a4928b8a2075788d360ee")
				.asJson();
		
//		System.out.println(country);
//		System.out.println(response.getStatus());
//		System.out.println(response.getHeaders().get("Content-Type"));
		return response;
	}

	/**
	 * Method will transform JSONObject into JSONArray in order to look for articles
	 * among the huge pile of information From there, method will only extract
	 * important information into JSONArray A 'for' loop is created in order to
	 * transform 'null' items into '-' Once its cleared in the loop, the Java Object
	 * will be formed and stored into a HashMap
	 * 
	 * @param response - JsonNode extracted from 'newsapi.org'
	 * @return - Return HashMap
	 */
	public static HashMap createHashMap(HttpResponse<JsonNode> response) {
		JSONObject json;
		JSONArray array;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		json = response.getBody().getObject();
		array = json.getJSONArray("articles");
		String aray = json.getJSONArray("articles").toString();
		HashMap temp = new HashMap();

		try {
			for (int i = 0; i < array.length(); i++) {
				JSONObject a = array.getJSONObject(i);
				JSONObject test = a.getJSONObject("source");
				sourceID = convertToJaveObj(test, "id");
				sourceName = convertToJaveObj(test, "name");
				publishedAt = convertToJaveObj(a, "publishedAt");
				author = convertToJaveObj(a, "author");
				urlToImage = convertToJaveObj(a, "urlToImage");
				description = convertToJaveObj(a, "description");
				title = convertToJaveObj(a, "title");
				url = convertToJaveObj(a, "url");
				content = convertToJaveObj(a, "content");
				country = convertToJaveObj(a, "country");
				news tempo = new news(publishedAt, author, urlToImage, description, title, url, content, sourceID,
						sourceName, country);
				temp.put(i, tempo);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return temp;
	}
}
