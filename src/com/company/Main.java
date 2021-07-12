package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        System.out.println("URL = https://lenta.ru/rss/news");
        System.out.println(readRSS("https://lenta.ru/rss/news"));
    }

    public static String readRSS(String URLAdress){
        try {
            URL rssURL = new URL(URLAdress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String sourceCode = "";
            String line;
            while ((line=in.readLine())!=null){
                if (line.contains("<title>")){
                    int firstPosition = line.indexOf("<title>");
                    String temp = line.substring(firstPosition);
                    temp = temp.replace("<title>", "");
                    int lastPosition = temp.indexOf("</title>");
                    temp = temp.substring(0, lastPosition);
                    sourceCode += temp+"\n";
                }
            }
            in.close();
            return sourceCode;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
