import java.io.IOException;
import java.util.Arrays;

public class Main {

    /*
    * 대나무 숲의 크기와 지역의 대나무 수를 이차배열에 입력한다.
    * 판다의 이동 방법은 상하좌우, 현 위치보다 다음 위치가 많을 경우 이동이 가능하다.
    * 지역마다 깊이 탐색을 이용해 해당 위치에서 상하좌우 움직일 수 있는지 확인을 하고 상하좌우로 움직였을 경우에 최대 움직임 수를 계산한다.
    * 모든 칸의 개수를 확인하기에는 오래 걸리기 때문에 다이나믹 프로그래밍을 이용해 한번 사용한 위치 값은 바로 불러온다.
    * */
    static int N;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        N = readNumber();

        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
            for (int j = 0; j < N; j++) {
                map[i][j] = readNumber();
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == -1) {
                    max = Math.max(max, bambooSearch(i, j));
                }
            }
        }

        System.out.println(max);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static int bambooSearch(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        int cnt = 1;
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            int temp = 1;
            if (isValid(nextX, nextY) && map[x][y] < map[nextX][nextY]) {
                temp += bambooSearch(nextX, nextY);
                cnt = Math.max(cnt, temp);
            }
        }

        dp[x][y] = cnt;
        return dp[x][y];
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}