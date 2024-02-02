import java.io.*;
import java.util.*;

public class Main {
    /*
    * 최단 경로를 구하기 위해서 너비 탐색을 이용한다.
    * 움직이는 방향은 4방향이고 0일 때 경로를 움직이고 1일 때는 움직이지 못한다.
    * 단, 1일 때 한 번만 부수고 이동할 수 있기 때문에 확인 해주는 게 필요하다.
    * 벽을 뚫고 지나간 경우와 벽을 뚫고 지나가지 않은 경우를 따로 표시해 경로를 이동할 수 있게 해준다.
    * */
    static char[][] map;
    static boolean[][][] visited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        // 간단하게 표시하기위해 char로 배열 선언
        map = new char[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
        }

        // 최단 거리 출력
        System.out.println(bfs());
    }

    private static int bfs() {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        Queue<int[]> queue = new LinkedList<>();
        visited[0][0][0] = true;
        queue.offer(new int[]{0, 0, 1, 0});
        while (!queue.isEmpty()) {
            int[] coordinate = queue.poll();
            int x = coordinate[0];
            int y = coordinate[1];
            int cnt = coordinate[2];
            int check = coordinate[3];

            // 도착지에서 거리 출력
            if (x == N-1 && y == M-1) {
                return cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && !visited[nextX][nextY][check]) {
                    // 일반적으로 길 지나가기
                    if (map[nextX][nextY] == '0') {
                        visited[nextX][nextY][check] = true;
                        queue.offer(new int[]{nextX, nextY, cnt + 1, check});
                    }
                    // 벽은 한번만 부실수 있다.
                    if (check == 0 && map[nextX][nextY] == '1') {
                        visited[nextX][nextY][1] = true;
                        queue.offer(new int[]{nextX, nextY, cnt + 1, 1});
                    }
                }
            }
        }
        return -1;
    }
}