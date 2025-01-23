import java.util.*;

/*  Problem: Given a variable number of days and temperature values
    (input by the user via keyboard).
    
    - Calculate the average temp, across all day's high 
    temperatures.
    - Calculate the number of days that were above the
    average.

Example input and output:

    How many days' temperatures? 7
    Day 1's high temp: 45
    Day 2's high temp: 44
    Day 3's high temp: 39
    Day 4's high temp: 48
    Day 5's high temp: 37
    Day 6's high temp: 46
    Day 7's high temp: 53
    Average temp = 44.6
    4 days were above average.

*/
public class Weather {
    public static void main (String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("How many days' temperatures? ");
        int days = console.nextInt();
        
        // Complete the remainder of the program using arrays.

        // Close console resource.
        console.close();
    }
}
