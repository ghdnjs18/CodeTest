import java.io.*;
import java.util.*;

public class Main {

    /*
    * 주어진 값 : 전깃줄의 개수 N, 전봇대 A와 B의 연결관계 N개
    * 목표 : 교차하는 연결을 제거한 최소 개수
    * 전봇대 A를 기준으로 전깃줄을 정렬해서 1번 줄부터 겹치지 않는 줄의 개수를 헤아린다
    * 총 전깃줄의 수에서 설치가 가능한 최대 줄의 수를 빼면 제외하는 전짓줄의 수이다. 
    * */

    public static void main(String[] args) throws IOException {
        int N = readNumber();

        int[][] wires = new int[N][2];
        for (int i = 0; i < N; i++) {
            wires[i][0] = readNumber();
            wires[i][1] = readNumber();
        }

        Arrays.sort(wires, (o1, o2) -> o1[0] - o2[0]);

        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (wires[i][1] > wires[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
        }
        
        System.out.println(N - max);
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