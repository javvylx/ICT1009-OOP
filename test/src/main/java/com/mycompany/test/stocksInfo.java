/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;

/**
 *
 * @author chinb
 */
public class stocksInfo {

    private String info, symbol, lastRef, interval, outputsize, timeZone;

    public stocksInfo(String info, String symbol, String lastRef, String interval, String outputsize, String timeZone) {
        this.info = info;
        this.symbol = symbol;
        this.lastRef = lastRef;
        this.interval = interval;
        this.outputsize = outputsize;
        this.timeZone = timeZone;
    }

    public String getInfo() {
        return info;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getlastRef() {
        return lastRef;
    }

    public String getInterval() {
        return interval;
    }

    public String getOutputsize() {
        return outputsize;
    }

    public String gettimeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        return "Information: " + info
                + "\nSymbol: " + symbol
                + "\nLast Refreshed:  " + lastRef
                + "\nInterval: " + interval
                + "\nOutput Size: " + outputsize
                + "\nTime Zone: " + timeZone;
    }
}
