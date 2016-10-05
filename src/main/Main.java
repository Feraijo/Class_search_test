package  main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
    private static Random r = new Random();

    private static char rChar(){
        return (char)(r.nextInt(25)+65);
    }

    public static void main(String[] args) {

        //Simulated input
        String[] names = new String[100000];
        long[] dates = new long[100000];

        Timer.start("Creating arrays");
        for (int i = 0; i<100000; i++) {
            names[i] = String.format("%c%c%c%c%c-%d", rChar(),rChar(),rChar(),rChar(),rChar(),i+1);
            long l = r.nextLong()/10000000000L;
            dates[i] = l > 0 ? l : l* (-1);
        }
        Timer.finish();

        Searcher s = new Searcher();
        //One-time indexing of data
        s.refresh(names, dates);
        //'exit' is breaking this cycle
        while (true) {
            try {
                BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the desired class: ");
                String query = rdr.readLine();

                if (query.equalsIgnoreCase("exit"))
                    break;

                //Search in string
                String[] results = s.guess(query);
                //Printing results
                for (String q : results) {
                    System.out.println(q);
                }
                System.out.println();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
