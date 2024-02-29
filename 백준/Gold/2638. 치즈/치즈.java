import java.io.IOException;

public class Main {
    /*
    * 주어진 치즈 정보를 이차배열에 저장한다.
    * 주어진 조건상 깊이 탐색, 너비 탐색 모두 가능할 것으로 예상된다.
    * 맨 가장자리는 치즈가 놓이지 않는다. -> 무조건 값이 0이다.
    * 가장자리에서 깊이 탐색을 이용해 외부 공기를 확인해 외부 공기와 내부 공기를 나눈다.
    * 외부 공기와 닿아있는 치즈 부분을 확인하고 해당 부분들만 깊이 탐색을 진행해준다.
    * 깊이 탐색으로 치즈에서 상하좌우 확인하여 외부 공기가 2칸 이상이면 0으로 만들어 치즈를 녹인다.
    * 전체 깊이 탐색을 반복하면서 녹는데 걸리는 시간을 구한다.
    * */

    static int N, M;
    static int[][] map;
    static int[][] check;
    static boolean[][] visited;
    static boolean[][] outsideAir;

    public static void main(String[] args) throws IOException {

        N = readNumber();
        M = readNumber();

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = readNumber();
            }
        }

        int time = 0;
        while (true) {
            visited = new boolean[N][M];

            // 외부 공기 표시
            outsideAir = new boolean[N][M];
            checkOutsideAir(0, 0);

            // 치즈 녹이기
            int meltedCheese = 0;
            check = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && isEdgeCheese(i, j) && !visited[i][j]) {
                        meltCheese(i, j);
                        meltedCheese++;
                    }
                }
            }

            if (meltedCheese == 0) break;

            time++;
        }
        System.out.println(time);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static void checkOutsideAir(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        visited[x][y] = true;
        outsideAir[x][y] = true;
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isValid(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] == 0) {
                checkOutsideAir(nextX, nextY);
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    private static boolean isEdgeCheese(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isValid(nextX, nextY) && outsideAir[nextX][nextY]) {
                return true;
            }
        }
        return false;
    }

    private static void meltCheese(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        visited[x][y] = true;
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (outsideAir[nextX][nextY]) {
                check[x][y] += 1;
                if (check[x][y] >= 2) {
                    map[x][y] = 0;
                }
            }
            if (map[nextX][nextY] == 1 && !visited[nextX][nextY] && isEdgeCheese(nextX, nextY)) {
                meltCheese(nextX, nextY);
            }
        }

    }
}