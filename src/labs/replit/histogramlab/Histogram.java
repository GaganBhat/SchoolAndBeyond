package labs.replit.histogramlab;
/**
 * The Histogram class contains the requested string and
 * has methods that create a histogram based on the frequency of
 * each character in the string without including whitespaces.
 *
 * @author Gagan Bhat
 * Collaborators: None
 * Teacher Name: Mrs. Ishman
 * Period: 3
 * Due Date: 3/30/2020
 */


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

public class Histogram
{
    private Map<String, Integer> histogram;
    private String requestedString;

    /**
     * Default constructor that initializes histogram to an empty map
     */
    public Histogram()
    {
        histogram = new TreeMap<>();
    }

    /**
     * Constructor with a param to accept the string and creates histogram
     * @param sent string to create histogram for
     */
    public Histogram(String sent)
    {
        this();
        this.requestedString = sent;
        setSentence();
    }

    /**
     * Creates a histogram in the TreeMap instance using requestedString instance
     */
    public void setSentence()
    {
        String[] characters = requestedString.replaceAll("\\s+","").split("");
        for(String character : characters)
            if(histogram.containsKey(character))
                histogram.put(character, histogram.get(character) + 1);
            else
                histogram.put(character, 1);
    }

    /**
     * Override method that changes the requested string and clears the current histogram
     * before creating the new one all in one method.
     * @param requestedString New requested string for histogram creation
     */
    public void setSentence(String requestedString){
        histogram = new TreeMap<>();
        this.requestedString = requestedString;
        setSentence();
    }

    /**
     * Converts the TreeMap key value pair into readable textual histogram
     * format for viewing by user.
     * @return string that contains readable textual histogram format
     * for viewing by user.
     */
    public String toString()
    {
        StringBuilder builder  = new StringBuilder();
        builder.append("char 1---5----01---5\n");
        for(Map.Entry<String, Integer> entry : histogram.entrySet()) {
            builder.append(entry.getKey() + "    ");
            for (int i = 0; i < entry.getValue(); i++)
                builder.append("*");
            builder.append("\n");
        }

        return builder.toString();
    }
}