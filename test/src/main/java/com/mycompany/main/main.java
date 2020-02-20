/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

import java.util.*;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.HttpResponse;


public class main {

    public static String pageSize = "100", cat = "top-headlines", q = "", symbol = "MSFT", country = "";

    public static void main(String[] args) throws Exception {
        HttpResponse<JsonNode> newsResponse = newsapiMethod.newsapi(q, cat, pageSize,country);
        HashMap Hashnews = newsapiMethod.createHashMap(newsResponse);

        HttpResponse<JsonNode> HstocksResponse = stocksapiMethod.Hstocksapi(symbol);
        HashMap HHashstocks = stocksapiMethod.createHashMap(HstocksResponse, 1);

        HttpResponse<JsonNode> DstocksResponse = stocksapiMethod.Dstocksapi(symbol);
        HashMap DHashstocks = stocksapiMethod.createHashMap(DstocksResponse, 2);

        HttpResponse<JsonNode> WstocksResponse = stocksapiMethod.Wstocksapi(symbol);
        HashMap WHashstocks = stocksapiMethod.createHashMap(WstocksResponse, 3);

        HttpResponse<JsonNode> MstocksResponse = stocksapiMethod.Mstocksapi(symbol);
        HashMap MHashstocks = stocksapiMethod.createHashMap(MstocksResponse, 4);
        
//        stocksDaily stockstmp = (stocksDaily) DHashstocks.get(1);
//        System.out.println(stockstmp.getStocksInfo());

//        /**
//         * FOR MY TEAMMATES - WHATSAPP THE GROUP IF YOU RUN INTO ANY ERROR, DONT KEEP QUIET. THANKS
//         * I have created 3 for loops to display information, comment out the necessary ones for it to run
//         * Check out the objects for various methods that you can call 
//         * 
//         * News class - news
//         * Stocks classes - stocksInfo, stocksDaily, stocksWeekly, stocksMonthly
//         * 
//         * 1 - For loop for news
              for (int i = 0; i < Hashnews.size(); i++) {
      			news newstemp = (news) Hashnews.get(i);
      			System.out.println(newstemp.toString());
  				}
//  				
  				
//         * 2 - For loop for stocks (change the Hashmap variable if need by for differe variations, e.g HHashstocks to WHstocks)
//                for (int i = 0; i < WHashstocks.size(); i++) {
//      			 stocksWeekly stockstemp = (stocksWeekly) WHashstocks.get(i);
//      			 System.out.println(stockstemp.toString());
//      			 }
//      for (int i = 0; i < HHashstocks.size(); i++) {
//		 stocksHourly stockstemp = (stocksHourly) HHashstocks.get(i);
//		 System.out.println(stockstemp.toString());
//		 }
//      for (int i = 0; i < DHashstocks.size(); i++) {
//		 stocksDaily stockstemp = (stocksDaily) DHashstocks.get(i);
//		 System.out.println(stockstemp.toString());
//		 }
//      for (int i = 0; i < MHashstocks.size(); i++) {
//		 stocksMonthly stockstemp = (stocksMonthly) MHashstocks.get(i);
//		 System.out.println(stockstemp.toString());
//		 }
//      for (int i = 0; i < WHashstocks.size(); i++) {
//		 stocksWeekly stockstemp = (stocksWeekly) WHashstocks.get(i);
//		 System.out.println(stockstemp.toString());
//		 }
        
//      for (int i = 0; i < HHashstocks.size(); i++) {
//		 stocksInfo stockstemp = (stocksInfo) HHashstocks.get(i);
//		 System.out.println(stockstemp.toString());
//		 }
//      
//          
//         * 3 - For loop for prediction stocks
//        			List<Double> valueDay = stockPrediction.predictionList(DHashstocks);
//       
//        			String[] DclosingPrediction = stockPrediction.calculate(symbol, valueDay);
//        			for (String each : DclosingPrediction) {
//            		System.out.println(each);
//        			}
        
    }
}
