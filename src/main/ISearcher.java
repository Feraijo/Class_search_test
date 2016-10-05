package main;

/**
 * Created by Feraijo on 23.09.2016.
 */
public interface ISearcher {
    /**
     * Refreshes internal data structures to find it easily
     * @param classNames project classes' names
     * @param modificationDates modification date in ms format since 1 jan 1970
     */
    void refresh(String[] classNames, long[] modificationDates);
    /**
     * Seeking a suitable class names
     * The name must begin with the 'start'
     * @param start the beginning of a class name
     * @return an array of length 0 to 12, class names, sorted by date
    modifications and lexicographic.
     */
    String[] guess(String start);
}
