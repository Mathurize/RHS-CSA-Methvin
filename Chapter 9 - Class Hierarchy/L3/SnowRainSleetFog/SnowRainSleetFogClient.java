public class SnowRainSleetFogClient {
    public static void main(String[] args) {
        // Example 1:
        Snow var1 = new Sleet();
        var1.method2();

        // Example 2:
        Snow var2 = new Rain();
        var2.method1();

        // Example 3:
        Snow var3 = new Rain();
        ((Sleet) var3).method3();

    }    
}
