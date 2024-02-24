import java.io.*;
import java.util.*;

public class Main {

    /*
    * 블록을 기준으로 15칸 안에 있는 가루로 연결된 램프들에 신호를 줄 수 있다.
    * 신호를 보낸 쪽으로 다시 넘오지 않게 하기 위해서 사용여부 배열이 필요하다 -> visited
    * 주어진 배열의 크기에 주어진 장치 별로 입력값을 받아 값을 다르게 넣어준다. block 1, dust 2, lamp 3
    * 블럭일 경우 이동을 해주기 위해 queue 좌표값을 넣어주고, 램프일 경우 횟수를 헤아려준다.
    * 상하좌우 방향으로 이동을하면서 이동시 이동횟수를 넘기며 반복한다.
    * dust(2)일 경우 이동해주고, lamp(3)일 경우 램프의 횟수를 감소 시키고 값을 0으로 변경한다.
    * 남은 램프가 있으면 모든 램프가 켜지지 않았으므로 failed, 없다면 success를 출력한다.
    * */
    static int W, H, lamp;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        W = Integer.parseInt(stringTokenizer.nextToken());
        H = Integer.parseInt(stringTokenizer.nextToken());
        int N = Integer.parseInt(bufferedReader.readLine());

        map = new int[W][H];
        visited = new boolean[W][H];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            String keyword = stringTokenizer.nextToken();
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            switch (keyword) {
                case "redstone_block":
                    map[x][y] = 1;
                    queue.offer(new int[]{x, y, 0});
                    visited[x][y] = true;
                    break;
                case "redstone_dust":
                    map[x][y] = 2;
                    break;
                case "redstone_lamp":
                    map[x][y] = 3;
                    lamp++;
                    break;
            }
        }

        bfs();

        System.out.println(lamp == 0 ? "success" : "failed");
    }

    private static void bfs() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];

            for (int i = 0; i < dx.length; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < W && nextY < H && cnt < 15 && !visited[nextX][nextY]) {
                    if (map[nextX][nextY] == 2) {
                        visited[nextX][nextY] = true;
                        queue.offer(new int[]{nextX, nextY, cnt + 1});
                    }
                    if (map[nextX][nextY] == 3) {
                        visited[nextX][nextY] = true;
                        map[nextX][nextY] = 0;
                        lamp--;
                    }
                }
            }
        }

    }
}