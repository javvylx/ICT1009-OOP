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
public class stocksHourlyDateTime {

    private String open, high, low, close, volume, dateTime;

    public stocksHourlyDateTime(String open, String high, String low, String close, String volume, String dateTime) {
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

    public String toString() {
        return "Open: " + open
                + "\nHigh: " + high
                + "\nLow: " + low
                + "\nClose: " + close
                + "\nVolume: " + volume
                + "\nDate Time: " + dateTime;
    }
}
