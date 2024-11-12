public class Words {
    public static void main(String[] args) {
        String word1 = "Bare";
        String word2 = "Blare";
        
        // Rhyme test
        if (word2.length() >= 2 && word1.endsWith(word2.substring(word2.length() - 2)))
            System.out.println("They rhyme!");

        // Alliteration test
        if (word1.startsWith(word2.substring(0, 1)))
            System.out.println("They alliterate!");
    }
}