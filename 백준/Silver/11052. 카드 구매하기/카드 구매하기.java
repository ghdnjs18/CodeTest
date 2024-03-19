import java.io.IOException;

public class Main {
    /*
    * 주어지는 값 : 구매하려 카드의 개수 N개, 카드 i개가 포함된 카드팩 금액 N개
    * 목표 : 가장 비싸게 원하는 카드 수 구매
    * 카드팩 금액을 기준으로 반복하면서 목표 카드 수에 가장 큰 금액을 구한다.
    * */

    static int N;
    static int[] price;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        N = readNumber();

        price = new int[N+1];
        dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
            price[i] = readNumber();
        }

        System.out.println(dp());
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

    private static int dp() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // j개의 카드를 구할 때, i개가 든 카드팩을 뽑을 수 있다면 
                if (j >= i) {
                    // j개의 카드를 뽑았던 값을 해당 카드를 뽑은 값에 뽑고 남은 카드의 값 더한 값을 비교해 큰 값으로 대체한다.
                    dp[j] = Math.max(dp[j], price[i] + dp[j-i]);
                }
            }
        }

        return dp[N];
    }
}