import java.io.*;
import java.util.*;

public class Main {
    /*
    * 사용되는 유형은 빈 공간, 울타리, 양, 늑대 4가지 이다.
    * 울타리 안에 공간은 한 공간으로 취급한다.
    * 한 공간안에 양이 늑대보다 많으면 늑대를 제거하고 같거나 적으면 양을 제거한다.
    * 모든 공간을 처리하고 남은 동물의 수를 양, 늑대 순으로 출력한다.
    * */

    static char[][] map;
    static boolean[][] visited;
    static int R, C, wolf, sheep;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            char[] line = bufferedReader.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = line[j];
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 울타리가 아닐 경우 너비 탐색을 한다.
                if (map[i][j] != '#' && !visited[i][j]) {
                    visited[i][j] = true;
                    bfs(i, j);
                }
            }
        }

        System.out.println(sheep + " " + wolf);
    }

    private static void bfs(int x, int y) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        int wolfCnt = 0;
        int sheepCnt = 0;
        // 처음 들어올 때, 양이나 늑대일 경우 헤어려준다.
        if (map[x][y] == 'o') {
            sheepCnt++;
        }
        if (map[x][y] == 'v') {
            wolfCnt++;
        }
        while (!queue.isEmpty()) {
            int[] move = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nextX = move[0] + dx[i];
                int nextY = move[1] + dy[i];

                // 배열안에서 울타리가 아닐 경우 움직여주고 양이나 늑대일 경우 횟수를 헤아려준다.
                if (nextX >= 0 && nextY >= 0 && nextX < R && nextY < C && !visited[nextX][nextY] && map[nextX][nextY] != '#') {
                    visited[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY});
                    if (map[nextX][nextY] == 'o') {
                        sheepCnt++;
                    }
                    if (map[nextX][nextY] == 'v') {
                        wolfCnt++;
                    }
                }
            }
        }
        
        // 양이 많다면 양의 수를 더해주고 아니면 늑대의 수를 더해준다.
        if (sheepCnt > wolfCnt) {
            sheep += sheepCnt;
        } else {
            wolf += wolfCnt;
        }
    }
}
