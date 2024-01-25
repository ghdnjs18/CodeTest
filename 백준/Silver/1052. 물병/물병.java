import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        if (N <= K) {
            System.out.println(0);
            return;
        }

        int cnt = 0;
        // 비트 마스킹 방식
        while (Integer.bitCount(N) > K) {
            cnt += N & (-N);
            N += N & (-N);
        }

        System.out.println(cnt);
    }
}