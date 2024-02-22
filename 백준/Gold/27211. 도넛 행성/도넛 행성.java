import java.io.*;
import java.util.*;

public class Main {
    /*
    * 일반적인 너비 탐색 문제에서 배열의 범위를 벗어 났을때 반대편으로 옮겨주는 로직을 추가한 문제
    * */

    static int N, M;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        N = readNumber();
        M = readNumber();

        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = readNumber() == 1 ? true : false;
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!map[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
    
    private static int readNumber() throws IOException {
        // 숫자를 입력 값으로 받아서 아스키값의 숫자는 48 ~ 57로 15(1111)와 비트 AND 연산을 했을 경우 0 ~ 9를 구할 수 있다.
        int n = System.in.read() & 15;
        int c = 0;
        // 다음 입력값이 숫자인지 확인해 한자리수를 늘려준다.
        // 비트 연산자 << 1 당 곱하기 2 -> (n * 8 + n * 2 = n * 10) + 새로운 1의 자리
        while ((c = System.in.read()) > 32) n = (n * 10) + (c & 15);
        // \r 일경우 탈출 문자는 read로 받아 다음 숫자에 영향이 안가게 해준다.
        if (c == 13) System.in.read();
        return n;
    }

    private static void bfs(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        map[x][y] = true;
        while (!queue.isEmpty()) {
            int[] move = queue.poll();
            int currentX = move[0];
            int currentY = move[1];

            for (int i = 0; i < dx.length; i++) {
                // (현재의 숫자 + (1 혹은 -1) + 해당 범위의 숫자) % 해당 범위의 숫자 = 0 ~ 해당 범위 - 1
                // (0 + 1 + 5) % 5 = 1, (0 - 1 + 5) % 5 = 4
                int nextX = (currentX + dx[i] + N) % N;
                int nextY = (currentY + dy[i] + M) % M;

                if (!map[nextX][nextY]) {
                    queue.offer(new int[]{nextX, nextY});
                    map[nextX][nextY] = true;
                }
            }
        }
    }
}