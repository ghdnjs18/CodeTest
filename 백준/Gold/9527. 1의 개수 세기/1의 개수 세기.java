import java.io.*;
import java.util.*;

public class Main {
    
    static int max = 57;
    static long[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        long A = Long.parseLong(stringTokenizer.nextToken()) - 1;
        long B = Long.parseLong(stringTokenizer.nextToken());

        nums = new long[max];
        nums[0] = 1;
        for (int i = 1; i < max; i++) {
            nums[i] = 2 * nums[i - 1] + (1L << i);
        }
        
        System.out.println(getOne(B) - getOne(A));
    }

    private static long getOne(long num) {
        long sum = num & 1;
        for (int i = max - 1; i > 0; i--) {
            if ((num & (1L << i)) != 0) {
                sum += nums[i - 1] + (num - (1L << i) + 1);
                num -= 1L << i;
            }
        }
        return sum;
    }
}