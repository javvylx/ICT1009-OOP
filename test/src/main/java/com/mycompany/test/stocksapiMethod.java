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
import java.util.*;
import org.json.*;
import java.text.*;

/**
 *
 * @author chinb
 */
public class stocksapiMethod {

    public static HttpResponse<JsonNode> Hstocksapi(String symbol) throws Exception {
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

    public static HttpResponse<JsonNode> Dstocksapi(String symbol) throws Exception {
        HttpResponse<JsonNode> response = Unirest.get("https://www.alphavantage.co/query?function={function}&symbol={symbol}&datatype={datatype}&apikey={APIkey}")
                .routeParam("function", "TIME_SERIES_DAILY")
                .routeParam("symbol", symbol)
                .routeParam("datatype", "json")
                .routeParam("APIkey", "82FVGVX8TQDW2B6V")
                .asJson();

        System.out.println(response.getStatus());
        System.out.println(response.getHeaders().get("Content-Type"));
        return response;
    }

    public static HttpResponse<JsonNode> Wstocksapi(String symbol) throws Exception {
        HttpResponse<JsonNode> response = Unirest.get("https://www.alphavantage.co/query?function={function}&symbol={symbol}&datatype={datatype}&apikey={APIkey}")
                .routeParam("function", "TIME_SERIES_WEEKLY")
                .routeParam("symbol", symbol)
                .routeParam("datatype", "json")
                .routeParam("APIkey", "82FVGVX8TQDW2B6V")
                .asJson();

        System.out.println(response.getStatus());
        System.out.println(response.getHeaders().get("Content-Type"));
        return response;
    }

    public static HttpResponse<JsonNode> Mstocksapi(String symbol) throws Exception {
        HttpResponse<JsonNode> response = Unirest.get("https://www.alphavantage.co/query?function={function}&symbol={symbol}&datatype={datatype}&apikey={APIkey}")
                .routeParam("function", "TIME_SERIES_MONTHLY")
                .routeParam("symbol", symbol)
                .routeParam("datatype", "json")
                .routeParam("APIkey", "82FVGVX8TQDW2B6V")
                .asJson();

        System.out.println(response.getStatus());
        System.out.println(response.getHeaders().get("Content-Type"));
        return response;
    }

    public static HashMap createHashMap(HttpResponse<JsonNode> response, int swap) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        json = response.getBody().getObject();
        if (swap == 1) {
            JSONObject jsonInfo = json.getJSONObject("Meta Data");
            String a = jsonInfo.getString("1. Information");
            String b = jsonInfo.getString("2. Symbol");
            String c = jsonInfo.getString("3. Last Refreshed");
            String d = jsonInfo.getString("4. Interval");
            String e = jsonInfo.getString("5. Output Size");
            String f = jsonInfo.getString("6. Time Zone");
            stocksInfo objInfo = new stocksInfo(a, b, c, d, e, f);
        }
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
        if (swap == 1) {
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
        } else {
            Collections.sort(al, new Comparator<String>() {
                DateFormat f = new SimpleDateFormat("yyyy-MM-dd");

                @Override
                public int compare(String o1, String o2) {
                    try {
                        return f.parse(o1).compareTo(f.parse(o2));
                    } catch (ParseException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
            });

        }
        HashMap temp1 = new HashMap();
        HashMap temp2 = new HashMap();
        HashMap temp3 = new HashMap();
        HashMap temp4 = new HashMap();
        for (int i = 0; i < al.size(); i++) {
            String tempDate = al.get(i);
            JSONObject tempObj = stocksObjHiLoDetails.getJSONObject(tempDate);
            String open = tempObj.getString("1. open");
            String high = tempObj.getString("2. high");
            String low = tempObj.getString("3. low");
            String close = tempObj.getString("4. close");
            String volume = tempObj.getString("5. volume");

            switch (swap) {
                case 1:
                    stocksHourlyDateTime HtempnewObj = new stocksHourlyDateTime(open, high, low, close, volume, tempDate);
                    temp1.put(i, HtempnewObj);
                case 2:
                    stocksDaily DtempnewObj = new stocksDaily(open, high, low, close, volume, tempDate);
                    temp2.put(i, DtempnewObj);
                case 3:
                    stocksWeekly WtempnewObj = new stocksWeekly(open, high, low, close, volume, tempDate);
                    temp3.put(i, WtempnewObj);
                case 4:
                    stocksMonthly MtempnewObj = new stocksMonthly(open, high, low, close, volume, tempDate);
                    temp4.put(i, MtempnewObj);
            }
        }

        switch (swap) {
            case 1:
                return temp1;
            case 2:
                return temp2;
            case 3:
                return temp3;
            case 4:
                return temp4;
        }

        return null;
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
