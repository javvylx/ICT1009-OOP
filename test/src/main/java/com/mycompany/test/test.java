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
import org.jsoup.nodes.Document;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

/**
 *
 * @author chinb
 */
public class test {

    public static String pageSize = "100", cat = "top-headlines", q = "", symbol = "MSFT";

    public static void main(String[] args) throws Exception { // Host url
        //HttpResponse<JsonNode> response = Unirest.get("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&outputsize=full&apikey=82FVGVX8TQDW2B6V")
        HttpResponse<JsonNode> newsResponse = newsapiMethod.newsapi(q, cat, pageSize);
        HttpResponse<JsonNode> stocksResponse = stocksapiMethod.stocksapi(symbol);
        HashMap Hashstocks = stocksapiMethod.createHashMap(stocksResponse);
        HashMap Hashnews = newsapiMethod.createHashMap(newsResponse);
        news newstemp = (news) Hashnews.get(1);
        
//System.out.println(temp.toString());
        for (int i = 0; i < Hashstocks.size(); i++) {
            stocksHourlyDateTime stockstemp = (stocksHourlyDateTime) Hashstocks.get(i);
            System.out.println(stockstemp.toString());
        }
//        String a = "sia";
//        Document doc;
//        try {
//            doc = Jsoup.connect("https://www.google.com/search?q="+a+"stock").userAgent("Chrome/79.0.3945.130").ignoreHttpErrors(true).timeout(0).get();
//
//            //print all available links on page
//            Elements links = doc.select("div#main");
//            for (Element l : links) {
//                Elements qqq = l.select("div.oPhL2e");
//                System.out.println("link: " + l);
//            }
////            for (Element link : links) {
//                System.out.println("penis");
//                Elements titles = link.select("div[id=oPhL2e]");
//                String title = titles.text();
//
//                Elements bodies = link.select("div[id=HfMth]");
//                String body = bodies.text();
//
//                System.out.println("Title: " + title);
//
//                System.out.println("Body: " + body + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//HttpResponse<JsonNode> stocksResponse = stocksapiMethod.stocksapi(q, cat, pageSize);
//        for (int i = 0;i < Hashnews.size();i++) {
//            news temp = (news) Hashnews.get(i);
//            System.out.println("Temp:  " + temp.geturl());
//        }
    }

}
