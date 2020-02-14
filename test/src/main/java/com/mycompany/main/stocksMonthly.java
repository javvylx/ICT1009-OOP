/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.main;

/**
 * Java Object - stocksMonthly
 * 
 * Parents class - stocksInfo
 * Child class - stocksHourly, stocksDaily, stocksWeekly, stocksMonthly
 * 
 * super(info, symbol, lastRef, interval, outputsize, timeZone) is called from parent
 * 
 * Method created here - getOpen, getHigh, getLow, getClose, getVolume, getdateTime, getSymbol, getTimeZone, getLastRef,
 * 						 getStocksInfo, toString
 */
public class stocksMonthly extends stocksInfo{
    
    private String open, high, low, close, volume, dateTime;

    public stocksMonthly(String info, String symbol, String lastRef, String interval, String outputsize, String timeZone,
    		String open, String high, String low, String close, String volume, String dateTime) {
    	super(info, symbol, lastRef, interval, outputsize, timeZone);
        this.dateTime = dateTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public String getOpen() {
        return open;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getClose() {
        return close;
    }

    public String getVolume() {
        return volume;
    }

    public String getdateTime() {
        return dateTime;
    }
    
    public String getSymbol() {
    	return symbol;
    }
    
    public String getTimeZone() {
    	return timeZone;
    }
    
    public String getLastRef() {
    	return lastRef;
    }
    
    public String getStocksInfo() {
    	return "Symbol: " + symbol
    			+ "\nTime Zone: " + timeZone
    			+ "\nLast updated: " + lastRef;
    }

    public String toString() {
        return "Open: " + open
                + "\nHigh: " + high
                + "\nLow: " + low
                + "\nClose: " + close
                + "\nVolume: " + volume
                + "\nDate Time: " + dateTime;
    }
}
