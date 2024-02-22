import java.io.*;
import java.util.*;

public class Main {
    /*
    * 일반적인 너비 탐색 문제에서 배열의 범위를 벗어 났을때 반대편으로 옮겨주는 로직을 추가한 문제
    * */

    static int N, M;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());


        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken()) == 1 ? true : false;
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!map[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    private static void bfs(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        map[x][y] = true;
        while (!queue.isEmpty()) {
            int[] move = queue.poll();
            int currentX = move[0];
            int currentY = move[1];

            for (int i = 0; i < dx.length; i++) {
                int nextX = currentX + dx[i];
                nextX = nextX < 0 ? N - 1 : nextX >= N ? 0 : nextX;
                // (현재의 숫자 + (1 혹은 -1) + 해당 범위의 숫자) % 해당 범위의 숫자 = 0 ~ 해당 범위 - 1
                // (0 + 1 + 5) % 5 = 1, (0 - 1 + 5) % 5 = 4
                int nextY = currentY + dy[i];
                nextY = nextY < 0 ? M - 1 : nextY >= M ? 0 : nextY;

                if (!map[nextX][nextY]) {
                    queue.offer(new int[]{nextX, nextY});
                    map[nextX][nextY] = true;
                }
            }
        }
    }
}