import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] map;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bufferedReader.readLine());

        map = new int[N + 1][N + 1];
        int max = 0;
        for (int i = 1; i <= N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        int answer = 0;
        for (int i = 1; i <= max; i++) {
            int cnt = 0;
            visit = new boolean[N + 1][N + 1];
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if (!visit[j][k] && map[j][k] >= i) {
                        cnt++;
                        visit[j][k] = true;
                        dfs(i, j, k);
                    }
                }
            }
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }

    private static void dfs(int height, int x, int y) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx > 0 && ny > 0 && nx <= N && ny <= N && !visit[nx][ny] && map[nx][ny] >= height) {
                visit[nx][ny] = true;
                dfs(height, nx, ny);
            }
        }

    }
}