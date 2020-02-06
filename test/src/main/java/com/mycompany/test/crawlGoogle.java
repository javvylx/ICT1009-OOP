/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;

import org.jsoup.nodes.Document;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import org.json.*;

/**
 *
 * @author chinb
 */
public class crawlGoogle {

    public static void main(String args[]) {
        Document doc;
        try {
            doc = Jsoup.connect("https://www.google.com/search?q=google+stock&rlz=1C1CHBF_enSG851SG851&oq=googel+stock&aqs=chrome.1.69i57j0l7.2830j0j7&sourceid=chrome&ie=UTF-8").userAgent("Chrome/79.0.3945.130").ignoreHttpErrors(true).timeout(0).get();
            Elements links = doc.select("duv[class=E65Bx]");
            for (Element link : links) {
                Elements titles = link.select("div[class=oPhL2e]");
                String title = titles.text();

                Elements bodies = link.select("div[class=HfMth]");
                String body = bodies.text();

                System.out.println("Title: " + title);
                System.out.println("Body: " + body + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
