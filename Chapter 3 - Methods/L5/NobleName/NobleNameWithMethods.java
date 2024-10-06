import java.util.*;

public class NobleNameWithMethods {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);

      // Noble Prompt
      System.out.print("Type your name, noble: ");
      String firstName = input.next();
      String lastName = input.next();

      System.out.println(buildNobleName(firstName, lastName));
      input.close();

      // Pass by value example
      int num1 = 10;
      int num2 = 13;

      System.out.println("num1 (main-before): " + num1);
      System.out.println("num2 (main-before): " + num2);
      System.out.println();

      System.out.println("passByValue returns: " + passByValueExample(num1, num2));
      System.out.println();

      System.out.println("num1 (main-after): " + num1);
      System.out.println("num2 (main-after): " + num2);
      System.out.println();
   }
   
   public static String buildNobleName(String first, String last) {
      String firstInitial = getFirstInitial(first);
      String nobleString = "\"Prime Minister " + first + " "
         + firstInitial + " " + last.toUpperCase()
         + ", esquire" + ", of Durdle Door.\"";

      return nobleString;
   }
   
   public static String getFirstInitial(String name) {
      return name.substring(0, 1) + ".";
   }

   public static int passByValueExample(int num1, int num2) {
      num1 += 5;
      num2 -= 3;

      System.out.println("num1 (method): " + num1);
      System.out.println("num2 (method): " + num2);
      System.out.println();

      return num1 + num2;
   }
}