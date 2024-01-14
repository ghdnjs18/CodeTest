import java.io.*;
import java.util.*;

public class Main {
    /* 문제 파악
    * 미로 이동에서 갈림길이 있을 경우가 있기 때문에 최소의 칸 수를 구하기 위해서는 DFS가 아닌 BFS를 사용해야 한다.
    * 좌표를 이동할 때, 좌표의 사용 여부를 판단해 왔던 길을 가지 않도록 해주고 움직일 때 마다 횟수를 헤아려준다.
    * 도착 지점에 도착시 이동 거래 출력
    * */
    static int N;
    static int M;
    static char[][] miro;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        // 입력 값으로 미로를 만들어준다.
        miro = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            miro[i] = bufferedReader.readLine().toCharArray();
        }
        bufferedReader.close();

        // 0,0 좌표 부터 1개로 헤아리면서 움직임 반복
        int answer = move(0, 0);

        System.out.println(answer);
    }

    public static int move(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, 1});
        int[] moveX = {1, -1, 0, 0};
        int[] moveY = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int dx = temp[0];
            int dy = temp[1];
            int cnt = temp[2];

            // 목적지 도착 시 움직인 횟수 확인
            if (dx == N-1 && dy == M-1) {
                return cnt;
            }

            // 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nextX = dx + moveX[i];
                int nextY = dy + moveY[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {
                    if (miro[nextX][nextY] == '1' && !visited[nextX][nextY]) {
                        queue.offer(new int[]{nextX, nextY, cnt + 1});
                        // 이동 표시
                        visited[nextX][nextY] = true;
                    }
                }
            }
        }
        return 0;
    }
}