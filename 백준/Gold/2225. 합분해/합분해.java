import java.io.IOException;

public class Main {
    /*
    * 주어진 값 : 정수의 범위 N(1~200), 정수를 뽑는 수 K(1~200)
    * 목표 : 0~N까지에서 K개를 더해 합이 N이 되는 경우의 수
    * 덧셈의 순서가 바뀐 경우 다른 경우이다 -> 순열
    * 한개의 수를 여러 번 사용할 수 있다.
    * 타뷸레이션 방식으로 점화식을 만들 수 있다.
    * */

    public static void main(String[] args) throws IOException {
        // 정수의 범위
        int N = readNumber();
        // 정수를 뽑는 수
        int K = readNumber();

        int[][] dp = new int[N+1][K+1];
        for (int i = 0; i <= N; i++) {
            // 하나를 뽑아 자신이 되는 수는 자신만 뽑는 경우이다.
            dp[i][1] = 1;
            for (int j = 1; j <= K; j++) {
                if (i == 0) {
                    // 몇장을 뽑든 0이 될 수 있는 경우는 0만 뽑는 경우이다.
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
            }
        }

        System.out.println(dp[N][K]);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        return flag ? -cur : cur;
    }
}