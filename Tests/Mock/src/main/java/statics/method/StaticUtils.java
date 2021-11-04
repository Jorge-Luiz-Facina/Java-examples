package statics.method;

public class StaticUtils {

    private StaticUtils() {}

    public static String wordReverse(String word) {
        int j = word.length();
        char[] newWord = new char[j];
        for (int i = 0; i < word.length(); i++) {
            newWord[--j] = word.charAt(i);
        }
        return new String(newWord);
    }

    public static String name() {
        return "Test";
    }
}