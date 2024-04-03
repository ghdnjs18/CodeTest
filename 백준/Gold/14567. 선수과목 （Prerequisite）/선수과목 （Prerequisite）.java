import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] prerequisites = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            prerequisites[i][0] = Integer.parseInt(st.nextToken());
            prerequisites[i][1] = Integer.parseInt(st.nextToken());
        }
        
        StringBuilder sb = new StringBuilder();

        int[] dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
            dp[i] = 1;
            for (int j = 0; j < M; j++) {
                if (i == prerequisites[j][1]) {
                    dp[i] = Math.max(dp[i], dp[prerequisites[j][0]] + 1);
                }
            }
            sb.append(dp[i]).append(" ");
        }
        
        System.out.println(sb);
    }
}