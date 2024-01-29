import java.io.*;
import java.util.*;

public class Main {
    /*
    * L와 W를 이용해 너비 우선 탐색을 한다.
    * L을 기준으로 너비 탐색을 해서 가장 긴 거리를 찾기까지 L들을 반복한다.
    * */

    static char[][] map;
    static boolean[][] visited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited = new boolean[N][M];
                if (map[i][j] == 'L') {
                    visited[i][j] = true;

                    max = Math.max(max, bfs(i, j));
                }
            }
        }

        System.out.println(max);
    }

    public static int bfs(int x, int y) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, 0});
        int cnt = 0;
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            cnt = temp[2];

            for (int i = 0; i < 4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && map[nextX][nextY] == 'L' && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;

                    queue.offer(new int[]{nextX, nextY, cnt + 1});
                }
            }
        }
        return cnt;
    }
}