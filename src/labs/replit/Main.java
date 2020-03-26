package labs.replit;

public class Main {

    public static void main(String[] args) {

        // Construct Histogram using the first sample data
        // Print the results
        Histogram hist = new Histogram("a b c d e f g h i a c d e g h i h k");
        System.out.println(hist);

        // Using the setSentence method, change the sentence using the remaining
        // 3 sample data case PLUS an additional data case that YOU make up.
        // Print the results of each sentence change

        hist.setSentence("1 2 3 4 5 6 1 2 3 4 5 1 3 1 2 3 4");
        System.out.println(hist);

        hist.setSentence("Y U I O Q W E R T Y");
        System.out.println(hist);

        hist.setSentence("4 T # @ ^ # # #");
        System.out.println(hist);

        hist.setSentence("h o p e f u l l y  t h i s  i s  r i g h t");
        System.out.println(hist);

    }

}
