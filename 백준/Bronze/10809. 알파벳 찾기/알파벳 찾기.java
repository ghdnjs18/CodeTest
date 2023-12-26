import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Map<Character, Integer> alphabetIndex = new HashMap<>();
        char alphabet = 'a';
        for (int i = 0; i < 26; i++) {
            alphabetIndex.put(alphabet++, -1);
        }

        char[] str = bufferedReader.readLine().toCharArray();

        for (int i = 0; i < str.length; i++) {
            if (alphabetIndex.get(str[i]) == -1) {
                alphabetIndex.put(str[i], i);
            }
        }
        
        for (int s : alphabetIndex.values()) {
            System.out.print(s + " ");
        }
    }
}