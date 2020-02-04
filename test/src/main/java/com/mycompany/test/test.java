/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import org.json.*;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.google.gson.*;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author chinb
 */
public class test {

    public static String urlToImage, publishedAt, author, description, title, url, content, temp;
    public static Integer check=0;

    public static String convertToJaveObj(JSONObject a, String b) {
        if (!a.isNull(b)) {
            temp = a.getString(b);
        } else {
            temp = "-";
        }
        return temp;
    }

    public static void main(String[] args) throws Exception { // Host url
        //HttpResponse<JsonNode> response = Unirest.get("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&outputsize=full&apikey=82FVGVX8TQDW2B6V")
        HttpResponse<JsonNode> response = Unirest.get("https://newsapi.org/v2/top-headlines?category=business&pageSize=100&apiKey=1e86e5f07d6a4928b8a2075788d360ee")
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getHeaders().get("Content-Type"));

        JSONObject json;
        JSONArray array;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        json = response.getBody().getObject();
        array = json.getJSONArray("articles");
        String aray = json.getJSONArray("articles").toString();

        HashMap treeMap = new HashMap();

        System.out.println();
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject a = array.getJSONObject(i);
                publishedAt = convertToJaveObj(a, "publishedAt");
                author = convertToJaveObj(a, "author");
                urlToImage = convertToJaveObj(a, "urlToImage");
                description = convertToJaveObj(a, "description");
                title = convertToJaveObj(a, "title");
                url = convertToJaveObj(a, "url");
                content = convertToJaveObj(a, "content");
                news penis = new news(publishedAt, author, urlToImage, description, title, url, content);
                treeMap.put(i, penis);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < treeMap.size(); i++) {
            news temp = (news)treeMap.get(i);
            System.out.println("Temp:  " + temp.geturl());
        }
    }

}
