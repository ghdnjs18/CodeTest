import java.io.*;
import java.util.*;

public class Main {

    /**
     * 주어진 값 : a의 개수 N, z의 개수 M, 문자열 순서 K
     * 조건 : a와 z로 만들 수 있는 문자열을 사전 순으로 정렬한다.
     * 목표 : K번째 문자열 출력
     * 문자열을 만들 수 있는 경우의 수는 종료가 a,z 뿐이기 때문에 두개의 합에서 하나를 뽑는 조합의 수와 같다.
     * dp방식을 이용하면 이전 개수의 경우의 수들을 더하면 해당 개수의 경우의 수를 구할 수 있다.
     * -> dp[n][m] = dp[n-1][m] + dp[n][m-1] -> 합이 1000000000을 넘을 수 없다.
     * a가 나오는 위치를 기준으로 K와 비교해서 a와 z를 구분해준다.
     */

    static int N, M, K;
    static int[][] dp;
    static StringBuilder stringBuilder;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        getCombination();

        stringBuilder = new StringBuilder();
        
        getString();

        System.out.println(stringBuilder);
    }

    private static void getCombination() {
        dp = new int[N + 1][M + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                // 처음 시작은 개수만큼 1을 넣어준다.
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                // a와 z의 이전 개수의 경우의 수를 더하면 현재의 경우의 수
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + dp[i][j - 1], 1000000000);
                }
            }
        }
    }

    private static void getString() {
        int aCnt = N;
        int zCnt = M;
        int aLocation = 0;

        // 경우의 수보다 목표 순서가 클 경우 출력이 불가능하다.
        if (K > dp[N][M]) {
            stringBuilder.append(-1);
            return;
        }

        for (int i = 0; i < N + M; i++) {

            // a 혹은 z가 없을 경우, 다른 문자만 올 수 있다.
            if (aCnt == 0) {
                stringBuilder.append("z");
                zCnt--;
            } else if (zCnt == 0) {
                stringBuilder.append("a");
                aCnt--;
            } else {
                // 현재 경우의 수에서 a가 앞에 올 수 있는 개수
                aLocation = dp[aCnt - 1][zCnt];
                
                // a가 올 수 있으면 a, 없으면 현재 개수를 빼 다음 자리를 찾도록 하고 z 출력
                if (K <= aLocation) {
                    stringBuilder.append("a");
                    aCnt--;
                } else {
                    K -= aLocation;
                    stringBuilder.append("z");
                    zCnt--;
                }
            }
            
        }
    }
}