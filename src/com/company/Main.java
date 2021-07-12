package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        System.out.println("URL = https://lenta.ru/rss/news"); // пометка, что мы будем брать
        System.out.println(readRSS("https://lenta.ru/rss/news")); // вывести на экран значения из метода с URL ссылкой
    }

    public static String readRSS(String URLAdress){ // наш метод с URL ссылкой
        try {
            URL rssURL = new URL(URLAdress); // запихиваем наш URL
            BufferedReader in = new BufferedReader(new InputStreamReader(rssURL.openStream())); // считываем поток данных в буфер
            String sourceCode = ""; // пустая строчка с будущим текстом
            String line; // переменная строка
            //в котору при помощи цикла, будем считывать по строчки из буфера
            while ((line=in.readLine())!=null){ // пока не пустой
                if (line.contains("<title>")){ // если находит в строчке наше слово
                    int firstPosition = line.indexOf("<title>"); // запоминаем его место
                    String temp = line.substring(firstPosition); // во временую строчку закиним новую, но обрезаную
                    temp = temp.replace("<title>", ""); // избавимься от нашего слова в строчке
                    int lastPosition = temp.indexOf("</title>"); // находим место окончания
                    temp = temp.substring(0, lastPosition); // обрезаем
                    sourceCode += temp+"\n"; // закидываем то что получилось в сточку которую вернём
                }
            }
            in.close(); // закрыть буфер
            return sourceCode; // вернуть значения
        }catch (Exception e){
            System.out.println(e); // ошибка
        }
        return null;
    }
}
