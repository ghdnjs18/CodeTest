import java.io.*;
import java.util.*;

public class Main {

    /*
    * 주어진 빙산 정보를 이차배열에 저장한다.
    * 년 수 마다 탐색이 진행되어야 하기 때문에 반복문 안에서 탐색을 진행한다.
    * 년 수 증가를 하고 깊이 탐색으로 빙산의 높이를 감소 시킨 후 너비 탐색으로 빙산의 수를 헤아린다.
    * 빙산의 수가 2개 이상이면 헤아린 년 수를 출력하고 빙산의 수가 0이면 0을 출력해준다.
    * 깊이 탐색
    * 1. 확인 되지 않은 전체 좌표를 반복하면서 빙산의 높이를 감소시킨다.
    * 2. 해당 좌표의 상하좌우를 판단해 0일 경우 재귀함수로 다음 좌표를 반복한다.
    * 3. 0이 아닐 경우 해당 좌표의 빙산의 높이를 감소 시킨다.
    * 너비 탐색
    * 1. 확인 되지 않은 전체 좌표를 반복하면서 빙산의 수를 헤아린다.
    * 2. 붙어 있는 0이 아닌 위치의 좌표를 반복한다.
    * 추가 사항
    * -> 처음부터 빙산의 수가 2개 일 수 있으니 너비 탐색을 먼저 해준다.
    * -> 깊이 탐색에서 높이 감소시에는 0이 되었을 때만 사용 처리를 해주어 감소가 중복되고 0일 때는 중복이 안되도록 해준다.
    * */

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        N = readNumber();
        M = readNumber();

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = readNumber();
            }
        }

        int yaer = 0;
        while (true) {
            int iceberg = 0;
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        visited[i][j] = true;
                        icebergCount(i, j);
                        iceberg += 1;
                    }
                }
            }

            if (iceberg >= 2) {
                System.out.println(yaer);
                break;
            }
            if (iceberg == 0) {
                System.out.println(0);
                break;
            }

            yaer += 1;

            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0 && !visited[i][j]) {
                        visited[i][j] = true;
                        icebergDecline(i, j);
                    }
                }
            }
        }
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static void icebergDecline(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && !visited[nextX][nextY]) {
                if (map[nextX][nextY] == 0) {
                    visited[nextX][nextY] = true;
                    icebergDecline(nextX, nextY);
                }
                if (map[nextX][nextY] != 0) {
                    map[nextX][nextY] -= 1;
                    if (map[nextX][nextY] == 0) {
                        visited[nextX][nextY] = true;
                    }
                }
            }
        }
    }

    private static void icebergCount(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nextX = cur[0] + dx[i];
                int nextY = cur[1] + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && map[nextX][nextY] != 0 && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }
    }
}