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
import java.util.*;
import org.json.*;
import java.text.*;

/**
 * 5 methods have been created here but essentially there is only 2 routes - creating hashmap(1 methods) and API pulling (4 methods)
 * 
 * API Pulling
 * All methods that returns HttpResponse<JsonNode> are extracting information from 'alphavantage.co'
 * Route parameters are specified in each method to obtain the results we desire
 * Extracting information from 4 methods is required as we require four sets of different data with different parameters
 * Once the information is extracted and transformed into a JsonNode, it will be returned to the main method for further processing
 * 
 * Parameters passed in
 * String symbol - This is the user input for the company symbol and will be used to find stocks information 
 */
public class stocksapiMethod {
	
	private static String a,b,c,d,e,f;

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

/**
 * Creating Hashmap stores converts all JsonNode into JavaObject where we can create our inheritance classes 
 * 
 * Parameters used
 * HttpResponse<JsonNode> response - JsonNode that was previously extracted via crawling. It contains all the information
 * and will be broken down into smaller parts
 * 
 * int swap - A counter to perform switch cases.
 * swap == 1 - It is extracting and store information into Hourly
 * swap == 2 - It is extracting and store information into Daily
 * swap == 3 - It is extracting and store information into Weekly
 * swap == 4 - It is extracting and store information into Monthly
 * 
 * Due to the sheer size of information, we will only extract specific items that is required and transform them
 * into parents and child classes
 * 
 * Parent class consist of
 * 1) Information
 * 2) Symbol
 * 3) Last Refreshed
 * 4) Interval
 * 5) Output Size
 * 6) Time Zone
 * 
 * Child class consist of
 * 1) Open
 * 2) High
 * 3) Low
 * 4) Close
 * 5) Volume
 */
    
    public static HashMap createHashMap(HttpResponse<JsonNode> response, int swap) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        json = response.getBody().getObject();
        
        /** In order to prevent re-iteration whenever this method is called, 
         * an 'if' condition is made as all object will have this information */
        if (swap == 1) {
            JSONObject jsonInfo = json.getJSONObject("Meta Data");
            a = jsonInfo.getString("1. Information");
            b = jsonInfo.getString("2. Symbol");
            c = jsonInfo.getString("3. Last Refreshed");
            d = jsonInfo.getString("4. Interval");
            e = jsonInfo.getString("5. Output Size");
            f = jsonInfo.getString("6. Time Zone");
        }
        
        /**
         * The following conditions are to sort the objects according to the date in an ascending mode
         * The JSONObject will be converted into JSONArray
         * Once converted, an iterator will loop through the entire array and transfer them into an ArrayList
         * In the ArrayList, a sort according to a SimpleDateFormat will be performed
         * E.g - "yyyy-MM-dd hh:mm:ss", All items will be sort according to month as the letter 'MM' is capatalized
         */
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
        
        /**
         * HashMap is created here in order to string all java objects together
         * Every object will be in their respective hashmap and returned back to the main function accordingly
         */
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
                    stocksInfo HtempnewObj = new stocksHourly(a,b,c,d,e,f, open, high, low, close, volume, tempDate);
                    temp1.put(i, HtempnewObj);
                case 2:
                    stocksInfo DtempnewObj = new stocksDaily(a,b,c,d,e,f, open, high, low, close, volume, tempDate);
                    temp2.put(i, DtempnewObj);
                case 3:
                    stocksInfo WtempnewObj = new stocksWeekly(a,b,c,d,e,f, open, high, low, close, volume, tempDate);
                    temp3.put(i, WtempnewObj);
                case 4:
                    stocksInfo MtempnewObj = new stocksMonthly(a,b,c,d,e,f, open, high, low, close, volume, tempDate);
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
    }
}
