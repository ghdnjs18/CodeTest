import java.io.*;

public class Main {

    /*
    * 주어진 값 : N일
    * 목표 : 개근상을 받을 수 있는 경우의 수 (지각을 두 번 이상 했거나, 결석을 세 번 연속 한 경우 제외)
    * 1일부터 N일 까지 일수를 추가하면서 개근이 되는 경우를 누적시키는 dp를 구현하면 될듯
    * 개근이 되는 경우는 지각 1번까지(0~1) 결석을 연속2번까지(0~2)의 경우이다
    * 일수, 지각수, 결석수를 기준으로 dp배열을 생성해 각경우에 수를 누적시킨다.
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] result = new int[N + 1];

        // 일수, 지각 횟수, 결석 횟수
        int[][][] dp = new int[N + 1][2][3];
        // 1일차 경우의 수
        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;
        result[1] = 3;

        // 2일차 부터 누적
        int mod = 1000000;
        for (int i = 2; i <= N; i++) {
            // 결석 연속 3번 하기전에 출석
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % mod;
            // 결석 1일차
            dp[i][0][1] = dp[i - 1][0][0] % mod;
            // 결석 2일차
            dp[i][0][2] = dp[i - 1][0][1] % mod;
            // 결석 연속 3번 하기전에 지각, 지각 1번 한 상태에서 결석 연속 3번 하기전에 출석
            dp[i][1][0] = (dp[i][0][0] + dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % mod;
            // 지각 1번 한 상태에서 결석 1일차
            dp[i][1][1] = dp[i - 1][1][0] % mod;
            // 지각 1번 한 상태에서 결석 2일차
            dp[i][1][2] = dp[i - 1][1][1] % mod;
            result[i] = (dp[i][0][0] + dp[i][0][1] + dp[i][0][2] + dp[i][1][0] + dp[i][1][1] + dp[i][1][2]) % mod;
        }

        System.out.println(result[N]);
    }
}