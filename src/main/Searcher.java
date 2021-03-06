package main;

import java.util.*;

    /**
     "Imagine that you - the developer from the ancient times, when IDE were only start to exist.
     You have big project in Java with hundreds of thousands files. To get your life more easy,
     you decided to implement the class search function by its name. For convenience, you enter
     only the first letters of the name of the class, IDE gives you a list of 12 classes that start
     with the characters entered. In the list classes are sorted by last modification (recently
     saved at the beginning), if changed at the same time - sorted lexicographically.

     It is expected that when the project is opened, data is indexed one time, then searches are
     performed quickly. That class search function will be used not only by yourself."

     Decision must have a class with a default constructor that implements the interface ISearcher.
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
        SortedMap<String, Long> map;
        //If search string is not empty:
        if (start.isEmpty()){
            map = classes;
        } else {
            //Searching string in map
            char nextLetter = (char) (start.charAt(start.length() - 1) + 1);
            String end = start.substring(0, start.length()-1) + nextLetter;
            //Result map
            map = classes.subMap(start, end);
        }
        List<Map.Entry<String, Long>> newestClasses = new ArrayList<>(map.entrySet());
        Collections.sort(newestClasses,
                new Comparator<Map.Entry<String, Long>>() {
                    @Override
                    public int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {
                        // Sort by modification date
                        int res = e2.getValue().compareTo(e1.getValue());
                        // If values are equal - sort by name
                        return res != 0 ? res : e1.getKey().compareTo(e2.getKey());
                    }
                }
        );

        // Returning results (no more than 12)
        String[] results = new String[Math.min(newestClasses.size(), 12)];

        for (int i = 0; i < results.length; i++) {
            results[i] = newestClasses.get(i).getKey() + " - "+ new Date(newestClasses.get(i).getValue());
        }
        Timer.finish();

        return results;
    }


}
