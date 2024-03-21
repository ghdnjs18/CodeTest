import java.io.IOException;

public class Main {
    /*
    * 주어진 값 : 시험의 단원 개수 N, 총 시간 T, 단원 별 에상 공부 시간 K, 문제 배점 S
    * 목표 : 총 시간 동안 공부해서 얻을 수 있는 최대 점수
    * 시간과 배점을 이용해서 배낭 문제처럼 풀 수 있을 것이다. -> 시간 - 무게, 배점 - 가치
    * 단원별 반복하면서 시간을 누적시켜 비교해 최대 점수를 얻는다.
    * */
    public static void main(String[] args) throws IOException {
        // 시험의 단원 개수
        int N = readNumber();
        // 총 학습 가능 시간
        int T = readNumber();

        // 단원 별 에상 공부 시간 및 점수 입력
        int[] times = new int[N+1];
        int[] scores = new int[N+1];
        int[][] dp = new int[N+1][T+1];
        for (int i = 1; i <= N; i++) {
            times[i] = readNumber();
            scores[i] = readNumber();
        }

        // 단원별 반복하면서 시간을 누적시켜 비교해 최대 점수를 얻는다.
        for (int i = 1; i <= N; i++) { // 단원
            for (int j = 1; j <= T; j++) { // 시간
                if (times[i] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-times[i]] + scores[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N][T]);
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
}