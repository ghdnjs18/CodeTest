import java.io.*;
import java.util.*;

public class Main {
    /*
    * 인접한 곳에 익지 않은 토마토는 익은 토마토에 의해서 익는다. => 너비 탐색을 이용해 6방향으로 탐색한다.
    * 다 익게 되는지 최소 일수 => 최적 경로 탐색
    * 다른 너비 탐색과 다른점은 높이로 움직이는 2방향이 추가 되어있어, 3차원 배열을 이용한다.
    * 익은 토마토에서 안익은 토마토로 전체적으로 증가하기 때문에 처음에 익은 토마토들을 체크하고 한번에 너비 탐색을 해준다.
    * */
    static int[][][] box;
    static boolean[][][] visited;
    static int M, N, H, cnt;
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        M = Integer.parseInt(stringTokenizer.nextToken());
        N = Integer.parseInt(stringTokenizer.nextToken());
        H = Integer.parseInt(stringTokenizer.nextToken());

        box = new int[H][N][M];
        visited = new boolean[H][N][M];

        // 입력에 맞게 토마토 박스에 넣기
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int k = 0; k < M; k++) {
                    box[i][j][k] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
        }

        cnt = 0;
        queue = new LinkedList<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (box[i][j][k] == 1) {
                        visited[i][j][k] = true;
                        queue.offer(new int[]{i, j, k, 0});
                    }
                }
            }
        }
        findTomato();

        // 아직 안익은 토마토가 있으면 모두 익지는 못하는 상황이다.
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (box[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        // 익는데 걸리는 시간을 출력한다.
        System.out.println(cnt);
    }

    private static void findTomato() {
        int[] dx = {1, -1, 0, 0, 0, 0};
        int[] dy = {0, 0, 1, -1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int x = temp[1];
            int y = temp[2];
            int z = temp[0];
            cnt = temp[3];

            for (int i = 0; i < 6; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                int nextZ = z + dz[i];

                if (nextX >= 0 && nextY >= 0 && nextZ >= 0 && nextX < N && nextY < M && nextZ < H) {
                    if (!visited[nextZ][nextX][nextY] && box[nextZ][nextX][nextY] == 0) {
                        box[nextZ][nextX][nextY] = 1;
                        visited[nextZ][nextX][nextY] = true;
                        queue.offer(new int[]{nextZ, nextX, nextY, cnt + 1});
                    }
                }
            }
        }
    }
}