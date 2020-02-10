/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 *
 * @author chinb
 */
public class stockPrediction {

    public static String[] calculate(String stockId, List<Double> value) {
        String[] values = new String[6];

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
        double closing1 = value.get(value.size() - 1) + avgChange;
        double closing2 = closing1 + avgChange;
        double closing3 = closing2 + avgChange;
        double closing4 = closing3 + avgChange;
        double closing5 = closing4 + avgChange;

        values[0] = stockId;
        values[1] = Double.toString(closing1);
        values[2] = Double.toString(closing2);
        values[3] = Double.toString(closing3);
        values[4] = Double.toString(closing4);
        values[5] = Double.toString(closing5);
//		values[5] = Double.toString(value.get(value.size() - 1));

        double quotient = avgChange;

        return values;
    }

    public static List<Double> predictionList(HashMap temp, int swap) {
        List<Double> pList1 = new ArrayList<>();
        List<Double> pList2 = new ArrayList<>();
        switch (swap) {
            case 1:
                for (int i = temp.size() - 1; i > temp.size() - 10; i--) {
                    stocksHourlyDateTime stockstemp1 = (stocksHourlyDateTime) temp.get(i);
                    Double a = Double.valueOf(stockstemp1.getClose());
                    pList1.add(a);
                }
                return pList1;
            case 2:
                for (int i = temp.size() - 1; i > temp.size() - 7; i--) {
                    stocksDaily stockstemp2 = (stocksDaily) temp.get(i);
                    Double a = Double.valueOf(stockstemp2.getClose());
                    pList2.add(a);
                }
                return pList2;
        }

        return null;
    }
}
