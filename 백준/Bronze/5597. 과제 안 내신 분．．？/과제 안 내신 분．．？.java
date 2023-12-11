import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Integer, Integer> map = new LinkedHashMap<>();

        for (int i = 1; i <= 30; i++) {
            map.put(i, 1);
        }

        for (int i = 0; i < 28; i++) {
            int N = Integer.parseInt(br.readLine());
            map.put(N, map.get(N) - 1);
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> integerEntry : map.entrySet()) {
            if (integerEntry.getValue() == 1) {
                min = min > integerEntry.getKey() ? integerEntry.getKey() : min;
                max = max < integerEntry.getKey() ? integerEntry.getKey() : max;
            }
        }

        System.out.println(min);
        System.out.println(max);
    }
}