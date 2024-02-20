import java.io.*;
import java.util.*;

public class Main {
    /*
    * 상의 움직이는 방법은 8가지 이다. 직선 움직임 한번에 대각선 2칸
    * 왕은 항상 궁성에 자리 잡고있다. -> 조건상 어디에 있든 아무상관이 없다
    * 다른 기물이 있다면 상은 움직일 수 없다. -> 입력 조건에 다른 기물이 없다? -> 왕이 경로에 있을 수 있다.
    * 상하좌우 한번, 대각선 한번 움직임에서 경로상 왕이 있는지 확인을 해야한다.
    * 상와 왕은 겹치지 않고 도달하지 못하면 -1 출력, 최소 이동 횟수를 출력하기 너비 탐색.
    * */
    static int startX, startY, endX, endY;
    static int[][] map = new int[10][9];
    static boolean[][] visited = new boolean[10][9];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = 0;
            }
        }

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        startX = Integer.parseInt(stringTokenizer.nextToken());
        startY = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        endX = Integer.parseInt(stringTokenizer.nextToken());
        endY = Integer.parseInt(stringTokenizer.nextToken());
        map[endX][endY] = 1;

        System.out.println(bfs());
    }

    private static int bfs() {
        int[] dx = {-3, 3, 2, -2, -3, 3, -2, 2};
        int[] dy = {-2, 2, -3, 3, 2, -2, -3, 3};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0});
        visited[startX][startY] = true;
        while (!queue.isEmpty()) {
            int[] move = queue.poll();
            int x = move[0];
            int y = move[1];
            int cnt = move[2];

            if (x == endX && y == endY) {
                return cnt;
            }

            for (int i = 0; i < dx.length; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];


                if (nextX >= 0 && nextY >= 0 && nextX < 10 && nextY < 9 && !visited[nextX][nextY] && check(x, y, i)) {
                    visited[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY, cnt + 1});
                }
            }
        }
        return -1;
    }

    private static boolean check(int x, int y, int index) {
        // 상하좌우
        int[] straightMoveX = {-1, 1, 0, 0};
        int[] straightMoveY = {0, 0, -1, 1};
        // 상하좌우 순으로 좌측 대각선, 우측 대각선
        int[] diagonalMoveX = {-1, 1, 1, -1, -1, 1, -1, 1};
        int[] diagonalMoveY = {-1, 1, -1, 1, 1, -1, -1, 1};

        int nextX = 0;
        int nextY = 0;
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                nextX = x + straightMoveX[index % 4];
                nextY = y + straightMoveY[index % 4];
            } else {
                nextX += diagonalMoveX[index];
                nextY += diagonalMoveY[index];
            }

            if (map[nextX][nextY] == 1) {
                return false;
            }
        }

        return true;
    }
}