import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 과목의 수 N, 조건의 수 M, M개의 연결 관계 A, B
    * 목표 : 과목 별 처리할 수 있는 최소 기간
    * 선수 조건의 수가 500000개 완전탐색은 불가능 -> DP, 그리디, 세그트리
    * A < B의 조건이 있기 때문에 1은 무조건 1 학기 만에 이수할 수 있다.
    * -> A를 기준으로 연결 관계를 이수할 수 있기 때문에 오름차순 정렬을 한다.
    * 해당 과목이 B번 과목에 있으면 연결된 A 과목 중 가장 큰값에서 +1을 해준다. 
    * */

    public static void main(String[] args) throws IOException {
        int N = readNumber();
        int M = readNumber();

        int[][] prerequisites = new int[M][2];
        for (int i = 0; i < M; i++) {
            prerequisites[i][0] = readNumber();
            prerequisites[i][1] = readNumber();
        }

        // A를 기준으로 전깃줄 정렬
        Arrays.sort(prerequisites, (o1, o2) -> o1[0] - o2[0]);

        int[] dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
            dp[i] = 1;

            for (int j = 0; j < M; j++) {
                if (i == prerequisites[j][1]) {
                    dp[i] = Math.max(dp[i], dp[prerequisites[j][0]] + 1);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.print(dp[i] + " ");
        }
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