import java.io.*;

public class Main {
    /*
     * 주어진 미로 정보를 이차배열에 저장한다.
     * 움직임의 조건은 미로 정보에 따라 상하좌우로 움직일 수 있다.
     * 각 칸에서 가능성을 확인해야 하기 때문에 배열 전체를 깊이 탐색으로 반복하면서 탈출 횟수를 헤아린다.
     * 깊이 탐색으로 탐색을 하면서 끝점이 배열 범위 밖인지, 사용유무를 판단해 한번 사용한 곳인지 확인해 탈출 가능성을 알 수 있다.
     * 탐색한 위치를 반복하지 않도록 저장하는 배여을 생성해 탈출 가능, 불가능, 미확인을 확인해준다.
     * */

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[][] check = new int[500][500];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = readNumber();
        M = readNumber();

        map = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (check[i][j] == 0) {
                    if (dfs(i, j)) {
                        cnt++;
                    }
                } else if (check[i][j] == 1) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static boolean dfs(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) {
            return true;
        }

        if (!visited[x][y]) {
            visited[x][y] = true;

            switch (map[x][y]) {
                case 'U':
                    if (dfs(x - 1, y)) {
                        check[x][y] = 1;
                    } else {
                        check[x][y] = 2;
                    }
                    break;
                case 'D':
                    if (dfs(x + 1, y)) {
                        check[x][y] = 1;
                    } else {
                        check[x][y] = 2;
                    }
                    break;
                case 'L':
                    if (dfs(x, y - 1)) {
                        check[x][y] = 1;
                    } else {
                        check[x][y] = 2;
                    }
                    break;
                case 'R':
                    if (dfs(x, y + 1)) {
                        check[x][y] = 1;
                    } else {
                        check[x][y] = 2;
                    }
                    break;
            }
        }

        if (check[x][y] == 1) return true;
        return false;
    }
}