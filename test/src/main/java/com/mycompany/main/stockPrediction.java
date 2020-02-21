/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
/**
 *
 * @author chinb
 */
public class stockPrediction {
	private static DecimalFormat df2 = new DecimalFormat("#.##");
    public static String[] calculate(String stockId, List<Double> value) {
        String[] values = new String[3];

        double avgChange = 0.00;
        double avgPrice = 0.00;
        double movingAvgPrice = 0.00;
        double movingAvgPriceTotal = 0.00;
        double previous = Double.MIN_VALUE;

        int count = 1;
        List<Double> movingAverage = new ArrayList<>();
        for (double each : value) {
            if (previous != Double.MIN_VALUE) {
                avgChange += (previous - each);
            }
            if (count % 100 == 0) {
                double currentMovingAvgPrice = movingAvgPrice / 100;
                movingAvgPriceTotal += currentMovingAvgPrice;
                movingAverage.add(currentMovingAvgPrice);
                movingAvgPrice = 0;
            }
            avgPrice += each;
            movingAvgPrice += each;
            previous = each;
            count++;
        }

        avgPrice /= value.size();
        avgChange /= value.size() - 1;

        double currentMovingAvgPrice = movingAvgPrice / (count % 100);
        movingAvgPriceTotal += currentMovingAvgPrice;

        movingAverage.add(currentMovingAvgPrice);
        movingAvgPrice = movingAvgPriceTotal / movingAverage.size();

//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug("Avg price :" + avgPrice + " Moving avg price :" + movingAvgPrice);
//        }
        
        df2.setRoundingMode(RoundingMode.DOWN);
        double closing1 = value.get(value.size() - 1) + avgChange;
        double closing2 = closing1 + avgChange;
        double closing3 = closing2 + avgChange;


        values[0] = df2.format(closing1).toString();
        values[1] = df2.format(closing2).toString();
        values[2] = df2.format(closing3).toString();
//		values[5] = Double.toString(value.get(value.size() - 1));

        double quotient = avgChange;

        return values;
    }

    public static List<Double> predictionList(HashMap temp) {
    	
    	
        List<Double> pList1 = new ArrayList<>();

        for (int i = temp.size() - 1; i > temp.size() - 3; i--) {
                    stocksDaily stockstemp = (stocksDaily) temp.get(i);
                    Double a = Double.valueOf(stockstemp.getClose());
                    pList1.add(a);     
        }
        return pList1;
    }
}
