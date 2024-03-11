import java.io.IOException;

public class Main {
    /*
    * 2^6까지는 빠른 처리속도를 체크하지 않아도 될 가능성이 높다.
    * 얼음의 정보를 입력하면서 총 얼음의 합을 구해준다.
    * 마법의 횟수를 반복해주면서 얼음을 섞고 녹여준다.
    * L이 0이 아니면 2^L의 크기에 따라 얼음 덩어리를 섞어준다.
    * 섞을 때는 x좌표 값에 L의 크기 만큼에 y의 증가량 + 1 만큼 빼주고, y좌표 값에는 x의 증가량을 더해준다.
    * 회전시킨 값을 원본 배열에 덮어준다.
    * 얼음을 녹일때는 상하좌우에 3개 이상의 얼음이 없으면 해당 총 얼음을 감소시킨다.
    * 해당 위치의 감소 값을 확인한 뒤 한번에 원본에 적용시켜준다. -> 감소하면서 녹은 얼음에 다른 얼음에 영향을 줄 수 있기 때문
    * 이후 그래프 탐색을 이용해 얼음 덩이의 크기를 구한다.
    * */

    static int N, Q, totalIce;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        N = (int) Math.pow(2, readNumber());
        Q = readNumber();

        totalIce = 0;
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = readNumber();
                if (num != 0) {
                    map[i][j] = num;
                    totalIce += num;
                }
            }
        }

        for (int i = 0; i < Q; i++) {
            int L = readNumber();

            // 얼음 섞기
            if (L != 0) {
                rotateIce((int) Math.pow(2, L));
            }
            // 얼음 녹이기
            meltIce();
        }
        System.in.close();

        int countIce = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = getCountIce(i, j);
                countIce = Math.max(countIce, cnt);
            }
        }

        System.out.println(totalIce);
        System.out.println(countIce);

    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static void rotateIce(int size) {
        for (int i = 0; i < N; i += size) {
            for (int j = 0; j < N; j += size) {
                rotate(i, j, size);
            }
        }
    }

    private static void rotate(int x, int y, int size) {
        int[][] temp = new int[size][size];

        // 90도 회전
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[i][j] = map[x + size - j - 1][y + i];
            }
        }

        // 원본에 복사
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[x + i][y + j] = temp[i][j];
            }
        }
    }

    private static void meltIce() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            temp[i] = map[i].clone();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0 && countAdjacent(i, j) < 3) {
                    temp[i][j] -= 1;
                    totalIce -= 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            map[i] = temp[i].clone();
        }
    }

    private static int countAdjacent(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int cnt = 0;
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isValid(nextX, nextY)) {
                cnt += 1;
            }
        }

        return cnt;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N && map[x][y] != 0;
    }

    private static int getCountIce(int x, int y) {
        if (!isValid(x, y) || visited[x][y]) return 0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        visited[x][y] = true;

        int cnt = 1;
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            cnt += getCountIce(nextX, nextY);
        }

        return cnt;
    }
}