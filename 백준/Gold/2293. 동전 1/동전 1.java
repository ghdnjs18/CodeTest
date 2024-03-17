import java.io.IOException;

public class Main {
    /*
    * 주어지는 값 : 동전의 수 N, 목표 수 K, 각 동전의 가치 N 개
    * 목표 : 동전의 합이 K가 되는 경우의 수
    * 사용한 동전의 구성이 같은데, 순서만 다른 경우는 포함 하지않는다. -> 조합
    * */

    static int N, K;
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        N = readNumber();
        K = readNumber();

        coins = new int[N];
        dp = new int[K+1];
        for (int i = 0; i < N; i++) {
            coins[i] = readNumber();
        }

        System.out.println(combination());
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return flag ? -cur : cur;
    }

    private static int combination() {

        dp[0] = 1; // 경우의 수 기본값
        for (int i = 0; i < N; i++) {
            for (int j = coins[i]; j <= K; j++) {
                // dp[j]의 경우의 수를 구하기 위해 해당 값에 해당 코인수를 뺀 경우의 수를 더해준다.
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }

        return dp[K];
    }
}