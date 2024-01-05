import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int L = Integer.parseInt(stringTokenizer.nextToken());
        int W = Integer.parseInt(stringTokenizer.nextToken());
        int H = Integer.parseInt(stringTokenizer.nextToken());

        long bigBox = (long) L * W * H;

        // 작은 상자가 큰 상자의 변보다 작아야한다.
        int min = Math.min(L, Math.min(W, H));

        double start = 0;
        double end = min;
        while (start < end) {
            double mid = (start + end) / 2;

            // 각 변을 mid값으로 나눠 나온 사각형들이 필요 개수 보다 적으면 더 작게 잘라 크기를 맞추도록 한다.
            // 소수점으로 무한반복을 방지하기 위해 동일한 수치가 반복되면 종료한다.
            if (((long)(L/mid) * (long)(W/mid) * (long)(H/mid)) < N) {
                if (end == mid) break;
                end = mid;
            } else {
                if (start == mid) break;
                start = mid;
            }
        }
        System.out.println(start);
    }
}