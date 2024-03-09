import java.io.*;
import java.util.*;

public class Main {
    /*
     * 사용하는 입력 값이 이차배열로 주워지면서 경로를 찾는다 -> 그래프 탐색 (깊이, 너비)
     * +는 벽으로 지나가지 못하고 .는 지나갈 수 있는 경로, 지나 가지 않은 경로는 @로 변경해 출력해야된다.
     * 그래프 탐색으로 지나가는 경로를 찾는게 더 쉽기 때문에 모든 .입력값을 @로 변경을 먼저 시킨다.
     * 이후 미로를 통과하는 경로를 찾아 해당 경로를 .으로 변경을 해준다.
     * 같은 지점으로 돌아오는 길이 존재하지 않아 순환되는 경우가 없고 출구는 단 2개만 주어진다.
     * 하나의 출구를 찾아 시작점으로 탐색을 하면서 다른 출구를 찾으면 해당 경로를 변경해주면 된다.
     * 시간 초과 -> 시작 경로를 @로 변경하지 않고 출구에서 출구로 나가는 경로를 새로운 배열로 체크를 하고
     * 출력시 경로로 체크되지 않은 지점을 @로 변경해서 출력해준다.
     * */

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static boolean[][] useSpot;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        // 미로의 크기
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        // 미로 정보 입력
        map = new char[N][M];
        visited = new boolean[N][M];
        useSpot = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
        }

        // 하나의 출구를 찾아 다른 출구로 나가는 경로를 찾는다.
        boolean check = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isExit(i, j) && !visited[i][j] && map[i][j] == '.') {
                    if (findExit(i, j)) {
                        check = true;
                        break;
                    };
                }
            }
            if (check) break;
        }

        // 변경된 미로 정보를 출력한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isExit(i, j) && !useSpot[i][j] && map[i][j] == '.') stringBuilder.append('@');
                else stringBuilder.append(map[i][j]);
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static boolean isExit(int x, int y) {
        return (x == 0 || y == 0 || x == N-1 || y == M-1);
    }

    private static boolean findExit(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        visited[x][y] = true;
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isValid(nextX, nextY)) {
                // 출구 탐색
                if (isExit(nextX, nextY)) {
                    visited[nextX][nextY] = true;
                    useSpot[nextX][nextY] = true;
                    useSpot[x][y] = true;
                    return true;
                }
                // 출구가 아닌 경로 탐색
                if (findExit(nextX, nextY)) {
                    useSpot[x][y] = true;
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M && !visited[x][y] && map[x][y] == '.';
    }
}