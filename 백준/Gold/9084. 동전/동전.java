import java.io.*;

public class Main {
    /*
     * 주어지는 값 : 테스트 케이스 T, 동전의 수 N, 목표 금액 M, 각 동전의 금액 N 개
     * 목표 : 테스트 케이스 만큼의 동전의 합이 M이 되는 경우의 수
     * 코인 개수 만큼 목표 금액까지 반복을 하면서 해당 동전으로 금액을 만들 수 있는 경우의 수를 누적시켜 목표 경우의 수를 구한다.
     * */

    static int[] coins;
    static int M;

    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        // 테스트 케이스
        int T = readNumber();

        for (int i = 0; i < T; i++) {
            // 동전의 가지 수
            int N = readNumber();
            coins = new int[N];

            // 동전의 금액
            for (int j = 0; j < N; j++) {
                coins[j] = readNumber();
            }

            // 목표 금액
            M = readNumber();

            stringBuilder.append(combination()).append("\n");
        }

        System.out.println(stringBuilder);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        return flag ? -cur : cur;
    }

    private static int combination() {
        int[] dp = new int[M + 1];
        dp[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= M; j++) {
                // dp[j]의 경우의 수를 구하기 위해 해당 값에 해당 코인수를 뺀 경우의 수를 더해준다.
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }

        return dp[M];
    }
}