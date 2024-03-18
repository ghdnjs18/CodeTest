import java.io.IOException;

public class Main {
    /*
     * 주어지는 값 : 동전의 수 N, 목표 수 K, 각 동전의 가치 N 개
     * 목표 : 동전의 합이 K가 되는 최소 동전의 개수, 불가능한 경우 -1 출력
     * 최소 개수를 출력해야 하기 때문에 dp배열을 상황에서 가장 큰값을 넣어줘야 한다. K 보다 1크게
     * 코인을 반복하면서 해당 코인의 필요한 수와 다른 코인으로 대체한 수를 더해 최소 개수를 구한다.
     * */

    static int N, K;
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        // 동전의 종류 수
        N = readNumber();
        // 목표 동전의 가치의 합
        K = readNumber();

        // 동전의 가치 입력
        dp = new int[K+1];
        coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = readNumber();
        }
        for (int i = 1; i <= K; i++) {
            dp[i] = K + 1;
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
        for (int i = 0; i < N; i++) {
            for (int j = coins[i]; j <= K; j++) {
                // 해당 코인의 필요한 수를 확인하기 위해 + 1을 해준다.
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        return dp[K] == K + 1 ? -1 : dp[K];
    }
}