package main;

import java.util.Date;

/**
 * Time-measuring support class.
 * Created by Feraijo on 23.09.2016.
 */
public class Timer {
    private static Date start;
    private static String process;

    /**
     * @param name Name of measuring process
     */
    public static void start(String name){
        process = name;
        start = new Date();
    }
    public static void finish(){
        Date stop = new Date();
        long time = stop.getTime() - start.getTime();
        System.out.println(process + ": " + time + "ms");
    }

}
