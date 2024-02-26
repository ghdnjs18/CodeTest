import java.io.*;
import java.util.*;

public class Main {
    /*
    * 입력값을 이용해 이차배열로 숫자배열의 형태를 만든다.
    * 각 위치에서 너비 탐색을 이용해 최단 경로로 이동을 해 이동 수와 시작점 끝점을 클래스를 이용해 구한다.
    * 이동 수를 비교해 가장 긴 경로를 확인하고 해당 경로의 시작점과 끝점의 가장 큰 합을 구해 출력한다.
    * 시작 점마다 최단 경로를 탐색해 너비 탐색마다 visited를 초기화 해줘야한다.
    * 시작 점과 끝 점이 동일한 위치일 수도 있다. -> 한점으로 끝날 수 있어 확인해주어야한다.
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

        int cnt = 0;
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    visited = new boolean[N][M];
                    Password password = bfs(i, j);
                    if (cnt < password.cnt) {
                        cnt = password.cnt;
                        total = password.start + password.end;
                    } else if (cnt == password.cnt) {
                        total = Math.max(total, password.start + password.end);
                    }
                }
            }
        }

        System.out.println(total);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static Password bfs(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<Password> queue = new LinkedList<>();
        queue.offer(new Password(x, y, 0, map[x][y], map[x][y]));
        visited[x][y] = true;
        Password cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && !visited[nextX][nextY] && map[nextX][nextY] != 0) {
                    queue.offer(new Password(nextX, nextY, cur.cnt + 1, cur.start, map[nextX][nextY]));
                    visited[nextX][nextY] = true;
                }
            }
        }

        return cur;
    }
}

class Password {
    int x;
    int y;
    int cnt;
    int start;
    int end;

    public Password(int x, int y, int cnt, int start, int end) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.start = start;
        this.end = end;
    }
}