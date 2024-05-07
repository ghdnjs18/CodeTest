import java.io.*;
import java.util.*;

public class Main {
    /**
     * 주어진 값 : N개의 수열, M개의 정수 S, 정수 E
     * 조건 : 앞 뒤에서 읽었을 경우 동일하면 팰린드롬이 성립한다.
     * 목표 : M개의 질문의 S에서 E까지의 수가 팰린드롬이 성립하는지 출력
     * 팰린드롬이 되려면 숫자의 범위가 홀수여야 한다.
     * 주어진 두 정수의 위치에서 끝의 숫자끼리 뺄 경우 0이 되면 성립한다.
     * 배열을 이용해서 성립 여부를 체크해두고 테스트 케이스에서 바로 출력한다. (N이 2000이니 4000000번 까지는 빠를지도?)
     */

    static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 수열의 크기
        int N = Integer.parseInt(bufferedReader.readLine());

        // 수열 입력
        sequence = new int[N];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = palindrome(i, j);
            }
        }

        // 질문의 개수
        int M = Integer.parseInt(bufferedReader.readLine());

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            // 수열의 개수
            int S = Integer.parseInt(stringTokenizer.nextToken());
            // 목표 합계
            int E = Integer.parseInt(stringTokenizer.nextToken());

            if (dp[S - 1][E - 1] == 0) {
                stringBuilder.append(1).append("\n");
            } else {
                stringBuilder.append(0).append("\n");
            }
        }

        System.out.println(stringBuilder);
    }

    private static int palindrome(int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (sequence[i] - sequence[j] != 0) {
                return 1;
            }
        }
        return 0;
    }
}