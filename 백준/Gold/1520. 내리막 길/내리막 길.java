import java.io.IOException;
import java.util.Arrays;

public class Main {
    /*
     * 내리막길 정보를 이차배열에 저장한다.
     * 움직임의 조건은 상하좌우로 움직일 수 있고 다음 숫자가 현재 숫자보다 작아야한다.
     * 배열의 끝 지점에 도착을 하는 횟수를 헤아린다. -> 깊이 탐색으로 확인이 가능하다.
     * 끝 지점에 도착한 경로를 체크해 한번더 이동할 필요없게 체크를 해준다.
     * */

    static int N, M;
    static int[][] map;
    static int[][] checked;

    public static void main(String[] args) throws IOException {
        N = readNumber();
        M = readNumber();

        map = new int[N][M];
        checked = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = readNumber();
            }
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(checked[i], -1);
        }

        System.out.println(routeSearch(0, 0));
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static int routeSearch(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        if (x == N - 1 && y == M - 1) {
            return 1;
        }
        if (checked[x][y] != -1) {
            return checked[x][y];
        }

        int cnt = 0;
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isValid(nextX, nextY) && map[x][y] > map[nextX][nextY]) {
                cnt += routeSearch(nextX, nextY);
            }
        }
        checked[x][y] = cnt;
        return checked[x][y];
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}