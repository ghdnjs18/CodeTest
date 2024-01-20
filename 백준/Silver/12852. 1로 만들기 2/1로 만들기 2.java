import java.io.*;

public class Main {
    /*
    * 연산 조건은 3 가지 이다.
    * 1. 3으로 나누어 떨어지면, 3으로 나눈다.
    * 2. 2로 나누어 떨어지면, 2로 나눈다.
    * 3. 1을 뺀다.
    * 1부터 목표 수까지 올라가면서 각 연산을 했을 때 최소 횟수를 구한다.
    * 해당 숫자를 연산 조건에 맞춰 진행하면서 최소 횟수를 비교해 연산을 진행한다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        int N = Integer.parseInt(bufferedReader.readLine());

        int[] dp = new int[N+1];
        // 1은 1이 되는 연산 횟수는 0부터 시작
        for (int i = 1; i <= N; i++) {
            dp[i] = i - 1;
        }

        for (int i = 2; i <= N; i++) {
            // 나눠질 경우의 횟수 최솟값 구하기
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i/3]);
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i/2]);
            }
            // 나눈 값과 -1했을 때의 값 중 횟수 최솟값
            dp[i] = Math.min(dp[i], dp[i-1]) + 1;
        }
        stringBuilder.append(dp[N]).append("\n");

        stringBuilder.append(N).append(" ");
        // 나눠질때, 나눴을 때 횟수가 한번만 감소 했을 경우 나눈다.
        while (N != 1) {
            if (dp[N - 1] == dp[N] - 1) {
                N = N - 1;
                stringBuilder.append(N).append(" ");
            } else if (N % 2 == 0 && dp[N / 2] == dp[N] - 1) {
                N = N / 2;
                stringBuilder.append(N).append(" ");
            } else {
                N = N / 3;
                stringBuilder.append(N).append(" ");
            }
        }

        System.out.println(stringBuilder);
    }
}