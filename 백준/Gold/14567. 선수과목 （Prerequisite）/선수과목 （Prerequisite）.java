import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 과목의 수 N, 조건의 수 M, M개의 연결 관계 A, B
    * 목표 : 과목 별 처리할 수 있는 최소 기간
    * 선수 조건의 수가 500000개 완전탐색은 불가능 -> DP, 그리디, 세그트리
    * A < B의 조건이 있기 때문에 1은 무조건 1 학기 만에 이수할 수 있다.
    * -> A를 기준으로 연결 관계를 이수할 수 있기 때문에 오름차순 정렬을 한다. -> 연결을 다 확인하기 떄문에 정렬할 필요 없음.
    * 해당 과목이 B번 과목에 있으면 연결된 A 과목 중 가장 큰값에서 +1을 해준다.
    * */

    // 그래프 비사용
    /*public static void main(String[] args) throws IOException {
        // 과목의 수
        int N = readNumber();
        // 연결의 수
        int M = readNumber();

        // 선수 과목 정보 입력
        int[][] prerequisites = new int[M][2];
        for (int i = 0; i < M; i++) {
            prerequisites[i][0] = readNumber();
            prerequisites[i][1] = readNumber();
        }

        StringBuilder stringBuilder = new StringBuilder();

        int[] dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
            dp[i] = 1;

            for (int j = 0; j < M; j++) {
                if (i == prerequisites[j][1]) {
                    dp[i] = Math.max(dp[i], dp[prerequisites[j][0]] + 1);
                }
            }
            stringBuilder.append(dp[i]).append(" ");
        }

        System.out.println(stringBuilder);
    }*/
    // 그래프 활용
    public static void main(String[] args) throws IOException {
        // 과목의 수
        int N = readNumber();
        // 연결의 수
        int M = readNumber();

        // 선수 과목 정보 입력
        int[][] prerequisites = new int[N + 1][N + 1];
        int[] degree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            int A = readNumber();
            int B = readNumber();

            prerequisites[A][B] = 1; // 단방향 그래프
            degree[B]++; // 선수 과목이 필요한 수 표시
        }

        // 큐로 위상정렬 : 선수 과목이 필요없는 과목들을 넣어준다.
        int[] dp = new int[N+1];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
                dp[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 1; i <= N; i++) {
                // 해당 과목을 필요로 하는 과목이 있으면 해당 과목이 필요한 수를 감소시킨다.
                if (prerequisites[cur][i] == 1) {
                    degree[i]--;

                    // 해당 과목이 마지막 선수 과목이였으면 큐에 넣어 주고 필요한 학기를 계산한다.
                    if (degree[i] == 0) {
                        queue.offer(i); // 해당과목에 선수과목이 있는지 확인하기 위해
                        dp[i] = dp[cur] + 1;
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            stringBuilder.append(dp[i]).append(" ");
        }

        System.out.println(stringBuilder);
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