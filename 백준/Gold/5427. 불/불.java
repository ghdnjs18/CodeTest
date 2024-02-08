import java.io.*;
import java.util.*;

public class Main {

    private static String[][] map;
    private static int H, W, cnt;
    private static Queue<int[]> fire = new LinkedList<>();
    private static Queue<int[]> human = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            H = Integer.parseInt(stringTokenizer.nextToken());
            W = Integer.parseInt(stringTokenizer.nextToken());
            cnt = 0;
            map = new String[W][H];
            fire = new LinkedList<>();
            human = new LinkedList<>();
            for (int j = 0; j < W; j++) {
                String[] temp = bufferedReader.readLine().split("");
                for (int k = 0; k < H; k++) {
                    map[j][k] = temp[k];
                    if (map[j][k].equals("*")) {
                        fire.offer(new int[]{j, k});
                    } else if (map[j][k].equals("@")) {
                        human.offer(new int[]{j, k, 0});
                    }
                }
            }

            bfs();

            if (cnt != 0) {
                stringBuilder.append(cnt).append("\n");
            } else {
                stringBuilder.append("IMPOSSIBLE").append("\n");
            }
        }
        System.out.println(stringBuilder);
    }

    private static void bfs() {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!human.isEmpty()) {

            int fireSize = fire.size();
            for (int i = 0; i < fireSize; i++) {
                int[] fireMove = fire.poll();

                for (int j = 0; j < dx.length; j++) {
                    int nextX = fireMove[0] + dx[j];
                    int nextY = fireMove[1] + dy[j];

                    if (nextX >= 0 && nextY >= 0 && nextX < W && nextY < H) {
                        if (map[nextX][nextY].equals(".") || map[nextX][nextY].equals("@")) {
                            map[nextX][nextY] = "*";
                            fire.offer(new int[]{nextX, nextY});
                        }
                    }
                }
            }

            int humanSize = human.size();
            for (int i = 0; i < humanSize; i++) {
                int[] humanMove = human.poll();

                for (int j = 0; j < dx.length; j++) {
                    int nextX = humanMove[0] + dx[j];
                    int nextY = humanMove[1] + dy[j];

                    if (nextX < 0 || nextY < 0 || nextX >= W || nextY >= H) {
                        cnt = humanMove[2] + 1;
                        return;
                    }

                    if (map[nextX][nextY].equals(".")) {
                        map[nextX][nextY] = "@";
                        human.offer(new int[]{nextX, nextY, humanMove[2] + 1});
                    }
                }
            }

        }
    }
}