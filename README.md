# Class_search_test
Test task from one employer i'd like to work with

Description of the problem
Imagine that you - the developer from the ancient times, when IDE were only start to exist. 
You have big project in Java with hundreds of thousands files.
To get your life more easy, you decided to implement the class search function by its name.
For convenience, you enter only the first letters of the name of the class, IDE gives you a list of 12
classes that start with the characters entered. 
In the list classes are sorted by last modification (recently saved at the beginning), if changed at the same time - sorted 
lexicographically. 

It is expected that when the project is opened, data is indexed one time, then searches are performed quickly.
That class search function will be used not only by yourself.

Decision must have a class with a default constructor that implements the interface ISearcher.
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
