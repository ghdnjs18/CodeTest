import java.io.*;

public class Main {
    /*
    * 평면을 분할할 때 규칙적으로 증가하기 때문에 다이나믹 프로그래밍을 이용해서 풀 수 있다.
    * 직선의 종류가 3가지로 최대 영역으로 분할하기 위해서는 다른 종류를 순차적으로 반복해야한다.
    * 직선의 종류의 배수 일때는 직선이 교차하는 점이 증가하지 않는다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bufferedReader.readLine());

        int[] dp = new int[T+1];
        dp[0] = 1;

        int line = 1;
        for (int i = 1; i <= T; i++) {
            dp[i] = dp[i - 1] + line;
            // 직선의 종류의 배수 일때는 직선이 교차하는 점이 증가하지 않는다.
            if (i % 3 != 0) line += 1;
        }

        System.out.println(dp[T]);
    }
}
