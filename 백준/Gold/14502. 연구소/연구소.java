import java.io.*;
import java.util.*;

public class Main {
    /*
    * 연구소 크기는 N * M으로 존재하고 0은 빈칸, 1은 벽, 2는 바이러스로 주어진다.
    * 바이러스는 상하좌우 인접한 빈 칸으로 이동하고 벽이 있으면 퍼지지 않는다.
    * 벽을 3개를 무조건 만들어야 하기 때문에 벽을 세울 수 있는 모든 경우의 수를 구한다.
    * 벽 3개가 만들어질 경우에 바이러스를 너비 탐색으로 퍼지는 범위를 확인하고 안전 구역의 크기를 구한다.
    * 여러번 반복해야하기 때문에 연구소 배열은 복사해서 사용을 한다.
    * */

    static int N, M, safeZone = 0;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = stringTokenizer.nextToken().charAt(0);
            }
        }

        makeWall(0);

        System.out.println(safeZone);
    }

    private static void makeWall(int cnt) {
        // 벽이 3개가 되는 경우의 수 마다 너비 탐색으로 바이러스의 수를 파악한다.
        if (cnt == 3) {
            virusSpread();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0') {
                    map[i][j] = '1';
                    makeWall(cnt + 1);
                    map[i][j] = '0';
                }
            }
        }
    }

    private static void virusSpread() {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // 반복을 위해 바이러스 위치 저장한다.
        Queue<int[]> virus = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '2') {
                    virus.offer(new int[]{i, j});
                }
            }
        }

        // 반복을 위해 배열을 복사해서 사용한다.
        char[][] copyMap = new char[N][M];
        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }

        while (!virus.isEmpty()) {
            int[] move = virus.poll();
            int x = move[0];
            int y = move[1];

            for (int i = 0; i < dx.length; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && copyMap[nextX][nextY] == '0') {
                    copyMap[nextX][nextY] = '2';
                    virus.offer(new int[]{nextX, nextY});
                }
            }
        }
        
        // 안전 영역 크기 확인
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == '0') {
                    cnt++;
                }
            }
        }
        safeZone = Math.max(safeZone, cnt);
    }
}