import java.io.*;

public class Main {
    /*
    * 주어진 방향대로 움직일 수 있는 위치 정보가 주어지고 해당 정보를 이차배열에 입력한다.
    * 세이프 존이 최소로 만들어지기 위해서는 반복되는 지점의 끝점에서 세이프 존이 만들어야 한다.
    * 주어진 배열의 크기가 1000 * 1000 이기 때문에 한번 지난 지점은 반복을 안하도록 조건을 설정해준다.
    * 문제에서 배열 밖으로 나가는 입력값은 주어지지 않기 때문에 해당 부분은 고려하지 않아도된다.
    * */

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[][] count;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = readNumber();
        M = readNumber();

        map = new char[N][M];
        visited = new boolean[N][M];
        count = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
        }

        int safezone = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    safezone += findSafezone(i, j);
                }
            }
        }

        System.out.println(safezone);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static int findSafezone(int x, int y) {

        int cnt = 0;
        if (!visited[x][y]) {
            if (count[x][y] == 2) {
                return 1;
            }
            count[x][y] += 1;
            switch (map[x][y]) {
                case 'U':
                    cnt = findSafezone(x - 1, y);
                    break;
                case 'D':
                    cnt = findSafezone(x + 1, y);
                    break;
                case 'L':
                    cnt = findSafezone(x, y - 1);
                    break;
                case 'R':
                    cnt = findSafezone(x, y + 1);
                    break;
            }

            visited[x][y] = true;
        }

        return cnt;
    }
}