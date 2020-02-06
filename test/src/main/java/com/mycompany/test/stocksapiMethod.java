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
import static com.mycompany.test.newsapiMethod.author;
import static com.mycompany.test.newsapiMethod.content;
import static com.mycompany.test.newsapiMethod.convertToJaveObj;
import static com.mycompany.test.newsapiMethod.country;
import static com.mycompany.test.newsapiMethod.description;
import static com.mycompany.test.newsapiMethod.publishedAt;
import static com.mycompany.test.newsapiMethod.sourceID;
import static com.mycompany.test.newsapiMethod.sourceName;
import static com.mycompany.test.newsapiMethod.title;
import static com.mycompany.test.newsapiMethod.url;
import static com.mycompany.test.newsapiMethod.urlToImage;
import java.util.*;
import org.json.*;
import java.text.*;
import java.time.format.*;
import java.time.*;

/**
 *
 * @author chinb
 */
public class stocksapiMethod {

    public static HttpResponse<JsonNode> stocksapi(String symbol) throws Exception {
        HttpResponse<JsonNode> response = Unirest.get("https://www.alphavantage.co/query?function={function}&symbol={symbol}&interval={interval}&datatype={datatype}&apikey={APIkey}")
                .routeParam("function", "TIME_SERIES_INTRADAY")
                .routeParam("symbol", symbol)
                .routeParam("interval", "60min")
                .routeParam("datatype", "json")
                .routeParam("APIkey", "82FVGVX8TQDW2B6V")
                .asJson();

        System.out.println(response.getStatus());
        System.out.println(response.getHeaders().get("Content-Type"));
        return response;
    }

    public static HashMap createHashMap(HttpResponse<JsonNode> response) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        json = response.getBody().getObject();
        JSONObject jsonInfo = json.getJSONObject("Meta Data");
        String a = jsonInfo.getString("1. Information");
        String b = jsonInfo.getString("2. Symbol");
        String c = jsonInfo.getString("3. Last Refreshed");
        String d = jsonInfo.getString("4. Interval");
        String e = jsonInfo.getString("5. Output Size");
        String f = jsonInfo.getString("6. Time Zone");
        stocksInfo objInfo = new stocksInfo(a, b, c, d, e, f);

        JSONArray stocksHiLo = new JSONArray();

        Iterator x = json.keys();
        while (x.hasNext()) {
            String key = (String) x.next();
            stocksHiLo.put(json.get(key));
        }
        JSONObject stocksObjHiLoDetails = new JSONObject();

        ArrayList<String> al = new ArrayList<String>();

        stocksObjHiLoDetails = stocksHiLo.getJSONObject(0);
        Iterator y = stocksObjHiLoDetails.keys();
        while (y.hasNext()) {
            String key = (String) y.next();
            al.add(key);
        }

        Collections.sort(al, new Comparator<String>() {
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            @Override
            public int compare(String o1, String o2) {
                try {
                    return f.parse(o1).compareTo(f.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        HashMap temp = new HashMap();
        for (int i = 0; i < al.size(); i++) {
            String tempDate = al.get(i);
            JSONObject tempObj = stocksObjHiLoDetails.getJSONObject(tempDate);
            String open = tempObj.getString("1. open");
            String high = tempObj.getString("2. high");
            String low = tempObj.getString("3. low");
            String close = tempObj.getString("4. close");
            String volume = tempObj.getString("5. volume");
            stocksHourlyDateTime tempnewObj = new stocksHourlyDateTime(open, high, low, close, volume, tempDate);
            temp.put(i, tempnewObj);
        }

        return temp;
//        System.out.println(stocksHiLoDetails);
//        Student student;
//        while (keys.hasNext()) {
//            String key = (String) keys.next();
//            student = new Student(key, jsonObj.optInt(key));
//            list.add(student);
//        }

//        String aray = json.getJSONArray("articles").toString();
////        System.out.println(aray);
//        HashMap temp = new HashMap();
//
//        try {
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject a = array.getJSONObject(i);
//                JSONObject test = a.getJSONObject("source");
//                sourceID = convertToJaveObj(test, "id");
//                sourceName = convertToJaveObj(test, "name");
//                publishedAt = convertToJaveObj(a, "publishedAt");
//                author = convertToJaveObj(a, "author");
//                urlToImage = convertToJaveObj(a, "urlToImage");
//                description = convertToJaveObj(a, "description");
//                title = convertToJaveObj(a, "title");
//                url = convertToJaveObj(a, "url");
//                content = convertToJaveObj(a, "content");
//                news penis = new news(publishedAt, author, urlToImage, description, title, url, content, sourceID, sourceName);
//                temp.put(i, penis);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return temp;
//    }
    }
}
