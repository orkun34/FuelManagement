package com.lombardi.fuelmng.util;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class SingletonHolder {

    private static SingletonHolder instance = null;
    private static Gson gsonInstance = null;

    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");


    private SingletonHolder() {
    }

    public static SingletonHolder getInstance() {
        if (instance == null) {
            instance = new SingletonHolder();
        }
        return instance;
    }

    public Gson getGson() {
        if (gsonInstance == null) {
            gsonInstance = new Gson();
        }
        return gsonInstance;
    }

    public static void main(String[] args) throws ParseException {


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = new Date();
        String parsedDate = formatter.format(newDate);
        System.out.println(parsedDate);
        String formattedDate = (formatter.format(formatter.parse(parsedDate)));
        System.out.println(formattedDate);
        Date kkk = new SimpleDateFormat("yyyy-MM-dd").parse(formattedDate);

        System.out.println("ababab");




       /* String input = "Fri Dec 07 00:00:00 EET 2018";
        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        //Date date = parser.parse(input);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        //System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(formattedDate));
        System.out.println(formatter.parse(formatter.format(formatter.parse(formattedDate))));
        System.out.println(formattedDate);*/
    }

}
