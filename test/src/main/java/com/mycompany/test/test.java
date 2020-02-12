/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;

import java.util.*;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.HttpResponse;

/**
 *
 * @author chinb
 */
public class test {

    public static String pageSize = "100", cat = "top-headlines", q = "", symbol = "MSFT";

    public static void main(String[] args) throws Exception {
        HttpResponse<JsonNode> newsResponse = newsapiMethod.newsapi(q, cat, pageSize);
        HashMap Hashnews = newsapiMethod.createHashMap(newsResponse);

        HttpResponse<JsonNode> HstocksResponse = stocksapiMethod.Hstocksapi(symbol);
        HashMap HHashstocks = stocksapiMethod.createHashMap(HstocksResponse, 1);

        HttpResponse<JsonNode> DstocksResponse = stocksapiMethod.Dstocksapi(symbol);
        HashMap DHashstocks = stocksapiMethod.createHashMap(DstocksResponse, 2);

        HttpResponse<JsonNode> WstocksResponse = stocksapiMethod.Wstocksapi(symbol);
        HashMap WHashstocks = stocksapiMethod.createHashMap(WstocksResponse, 3);

        HttpResponse<JsonNode> MstocksResponse = stocksapiMethod.Mstocksapi(symbol);
        HashMap MHashstocks = stocksapiMethod.createHashMap(MstocksResponse, 4);
//        news newstemp = (news) Hashnews.get(1);
//        for (int i = 0; i < DHashstocks.size(); i++) {
//            stocksDaily stockstemp = (stocksDaily) DHashstocks.get(i);
//            System.out.println(stockstemp.toString());
//        }

        List<Double> valueHour = stockPrediction.predictionList(HHashstocks, 1);
        List<Double> valueDay = stockPrediction.predictionList(DHashstocks, 2);
//        
//        String[] HclosingPrediction = stockPrediction.calculate(symbol, valueHour);
//        for (String each : HclosingPrediction) {
//            System.out.println(each);
//        }
//        String[] DclosingPrediction = stockPrediction.calculate(symbol, valueDay);
//        for (String each : DclosingPrediction) {
//            System.out.println(each);
//        }
        
        for (int i = 0; i < HHashstocks.size(); i++) {
            stocksHourlyDateTime stockstemp = (stocksHourlyDateTime) HHashstocks.get(i);
            System.out.println(stockstemp.toString());
        }
    }

}
