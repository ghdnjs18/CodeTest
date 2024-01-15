import java.io.*;
import java.util.*;

public class Main {
    /*
    * 테스트 케이스가 여러게 주어지기 때문에 맵 구성을 그때마다 해주어야한다.
    * 주어진 크기의 배열에 좌표값에 따라 표시를 하고 BFS방식으로 이동을하면서 범위를 확인한다.
    * */

    static int M;
    static int N;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            M = Integer.parseInt(stringTokenizer.nextToken());
            N = Integer.parseInt(stringTokenizer.nextToken());
            int K = Integer.parseInt(stringTokenizer.nextToken());

            // 입력 값으로 배추가 있는 부분을 표시해준다.
            map = new boolean[M][N];
            for (int j = 0; j < K; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int X = Integer.parseInt(stringTokenizer.nextToken());
                int Y = Integer.parseInt(stringTokenizer.nextToken());

                map[X][Y] = true;
            }

            // 배추가 심어진 위치를 확인해 상하좌우 움직여 붙은 배추들을 헤아린다.
            int cnt = 0;
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k]) {
                        map[j][k] = false;
                        move(j, k);

                        cnt++;
                    }
                }
            }

            stringBuilder.append(cnt).append("\n");
        }

        System.out.println(stringBuilder);
    }

    public static void move(int x, int y) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX >= 0 && nextY >= 0 && nextX < M && nextY < N && map[nextX][nextY]) {
                map[nextX][nextY] = false;
                move(nextX, nextY);
            }
        }
    }
}