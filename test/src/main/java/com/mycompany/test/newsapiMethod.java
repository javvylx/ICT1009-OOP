/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;

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
 *
 * @author chinb
 */
public class newsapiMethod extends test {

    public static String urlToImage, publishedAt, author, description, title, url, content, sourceID, sourceName, temp;
    public static String pageSize = "100", cat = "top-headlines", q = "", country = "us";

    public static String convertToJaveObj(JSONObject a, String b) {
        if (!a.isNull(b)) {
            temp = a.getString(b);
        } else {
            temp = "-";
        }
        return temp;
    }

    public static HttpResponse<JsonNode> newsapi(String q, String cat, String pageSize) throws Exception {
        HttpResponse<JsonNode> response = Unirest.get("https://newsapi.org/v2/{cat}?country={country}&category=business&q={keywords}&pageSize={pageSize}&apiKey={APIkey}")
                .routeParam("cat", cat)
                .routeParam("country", country)
                .routeParam("keywords", q)
                .routeParam("pageSize", pageSize)
                .routeParam("APIkey", "1e86e5f07d6a4928b8a2075788d360ee")
                .asJson();

        System.out.println(response.getStatus());
        System.out.println(response.getHeaders().get("Content-Type"));
        return response;
    }

    public static HashMap createHashMap(HttpResponse<JsonNode> response) {
        JSONObject json;
        JSONArray array;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        json = response.getBody().getObject();
        array = json.getJSONArray("articles");
        String aray = json.getJSONArray("articles").toString();
//        System.out.println(aray);
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
                news penis = new news(publishedAt, author, urlToImage, description, title, url, content, sourceID, sourceName);
                temp.put(i, penis);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return temp;
    }
}
