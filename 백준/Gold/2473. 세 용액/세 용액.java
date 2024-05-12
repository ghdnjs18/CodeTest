import java.io.*;
import java.util.*;

public class Main {

    static long min = Long.MAX_VALUE;
    static long[] result = new long[3];
    static long[] solutions;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 용액의 수
        int N = Integer.parseInt(bufferedReader.readLine());

        // 용액의 정보 입력
        solutions = new long[N];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        // 용액 정렬
        Arrays.sort(solutions);

        for (int left = 0; left < N - 2; left++) {
            int mid = left + 1;
            int right = N - 1;

            while (mid < right) {
                long sum = solutions[left] + solutions[mid] + solutions[right];
                getMin(left, mid, right);

                if (sum < 0) {
                    mid++;
                } else {
                    right--;
                }
            }

            if (min == 0) break;
        }

        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }

    private static void getMin(int left, int mid, int right) {
        if (min > Math.abs(solutions[left] + solutions[mid] + solutions[right])) {
            min = Math.abs(solutions[left] + solutions[mid] + solutions[right]);
            result[0] = solutions[left];
            result[1] = solutions[mid];
            result[2] = solutions[right];
        }
    }
}