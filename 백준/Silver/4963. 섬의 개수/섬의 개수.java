import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int W = Integer.parseInt(stringTokenizer.nextToken());
            int H = Integer.parseInt(stringTokenizer.nextToken());

            if (W == 0 && H == 0) break;

            int[][] map = new int[H][W];
            boolean[][] visited = new boolean[H][W];
            for (int i = 0; i < H; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }

            Queue<IslandPoint> queue = new LinkedList<>();
            int cnt = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == 1 && !visited[i][j]) {
                        queue.offer(new IslandPoint(i, j));
                        while (!queue.isEmpty()) {
                            IslandPoint point = queue.poll();

                            if (point.x >= 0 && point.y >= 0 && point.x < H && point.y < W && map[point.x][point.y] == 1 && !visited[point.x][point.y]) {
                                visited[point.x][point.y] = true;

                                for (int k = 0; k < 8; k++) {
                                    queue.offer(new IslandPoint(point.x + dx[k], point.y + dy[k]));
                                }

                            }
                        }
                        cnt++;
                    }
                }
            }
            bufferedWriter.write(cnt + "\n");
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}

class IslandPoint {
    public int x;
    public int y;

    public IslandPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}