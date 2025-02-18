import java.io.*;
import java.util.*;

public class AppMain {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("words.txt"));
        ArrayList<String> words = new ArrayList<String>();

        while (input.hasNextLine()) {
            words.add(input.nextLine());
        }

        System.out.println(words);

        // close file handle
        input.close();
    }
}