package main;

import java.util.*;

/**
 * Created by Feraijo on 23.09.2016.
 */
class Searcher implements ISearcher {
    private SortedMap<String, Long> classes = new TreeMap<>();

    @Override
    public synchronized void refresh(String[] classNames, long[] modificationDates) {
        Timer.start("One-time indexing");
        /*
        Indexing data - adding arrays sorted by map keys.
        */
        for (int i = 0; i< classNames.length; i++){
            classes.put(classNames[i], modificationDates[i]);
        }
        Timer.finish();
    }

    @Override
    public synchronized String[] guess(String start) {
        Timer.start("Search");

        ArrayList<String> list = new ArrayList<>(); //auxiliary list for transferring data from map to array

        String[] result; //resulting list of class names
        //If search string is not empty:
        if(start.length() > 0) {
            //Searching string in map
            char nextLetter = (char) (start.charAt(start.length() - 1) + 1);
            String end = start.substring(0, start.length()-1) + nextLetter;
            //Result map
            SortedMap<String, Long> map = classes.subMap(start, end);
            //Sort all results by last mod.date
            Map <String, Long> sortedSet = sortByValue(map);
            list.addAll(sortedSet.keySet());
            //Printing results (no more than 12)
            if (map.size() < 12) {
                result = new String[map.size()];
                for (int i = 0; i < map.size(); i++) {
                    result[i] = list.get(i);
                }
            } else {
                result = new String[12];
                for (int i = 0; i < 12; i++) {
                    result[i] = list.get(i);
                }
            }
            Timer.finish();
            return result;
        }
        //If search string is empty - 12 last modified classes printed.
        list.addAll(sortByValue(classes).keySet());
        result = new String[12];
        for (int i = 0; i<12; i++){
            result[i] = list.get(i);
        }
        Timer.finish();
        return result;
    }

    /*
    Auxiliary method, creating linked hash map to sort by value instead of key.
    */
    private static Map<String, Long> sortByValue(Map<String, Long> map) {
        LinkedHashMap<String, Long> result = new LinkedHashMap<>();
        //Create a set of records <String, Long>, to implement custom comparator.
        SortedSet<Map.Entry<String, Long>> sortedEntries = new TreeSet<>(
                new Comparator<Map.Entry<String, Long>>() {
                    @Override
                    public int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {
                        //Sort by value
                        int res = e1.getValue().compareTo(e2.getValue());
                        //If values are equal - sort by key
                        return res != 0 ? res : e1.getKey().compareTo(e2.getKey());
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());

        for (Map.Entry<String, Long> e : sortedEntries){
            result.put(e.getKey(), e.getValue());
        }

        return result;
    }
}
