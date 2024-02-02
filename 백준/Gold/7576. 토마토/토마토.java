import java.io.*;
import java.util.*;

public class Main {
    /*
     * 인접한 곳에 익지 않은 토마토는 익은 토마토에 의해서 익는다. => 너비 탐색을 이용해 4방향으로 탐색한다.
     * 다 익게 되는지 최소 일수 => 최적 경로 탐색
     * 익은 토마토에서 안익은 토마토로 전체적으로 증가하기 때문에 처음에 익은 토마토들을 체크하고 한번에 너비 탐색을 해준다.
     * */
    static int[][] box;
    static int M, N, cnt = 0;
    static Queue<int[]> queue = new LinkedList<>();;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        M = Integer.parseInt(stringTokenizer.nextToken());
        N = Integer.parseInt(stringTokenizer.nextToken());

        box = new int[N][M];

        // 입력에 맞게 토마토 박스에 넣기
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (box[i][j] == 1) {
                    queue.offer(new int[]{i, j, 0});
                }
            }
        }

        // 안익은 토마토 너비 탐색
        findTomato();

        // 아직 안익은 토마토가 있으면 모두 익지는 못하는 상황이다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        // 익는데 걸리는 시간을 출력한다.
        System.out.println(cnt);
    }

    private static void findTomato() {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];
            cnt = temp[2];

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && box[nextX][nextY] == 0) {
                    box[nextX][nextY] = 1;
                    queue.offer(new int[]{nextX, nextY, cnt + 1});
                }
            }
        }
    }
}