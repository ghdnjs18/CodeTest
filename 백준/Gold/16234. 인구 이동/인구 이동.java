import java.io.*;
import java.util.*;

public class Main {

    /*
    * 주어진 범위의 배열을 전체적으로 너비 탐색을 이용해 진행을 하고 변경된 인구로 다시 너비 탐색이 되는지 확인을 해야한다.
    * 너비 탐색을 하면서 연합을 이루는 인구수와 연합을 이루는 좌표를 별도로 관리해야 한다.
    * 인구수를 더해줄 때의 위치를 잘 생각해 중복이 안되도록 해야한다.
    * 너비 탐색으로 나온 연합의 인구수를 잘 분배해줘야한다.
    * */
    private static int N, L, R;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        L = Integer.parseInt(stringTokenizer.nextToken());
        R = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int cnt = 0;
        while (true) {
            int flag = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        flag += bfs(i, j);
                    }
                }
            }
            // 국경선이 열린 적이없으면 종료시켜준다.
            if (flag == 0) break;

            cnt++;
        }
        System.out.println(cnt);
    }

    private static int bfs(int x, int y) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int sum = 0;
        int div = 0;
        int flag = 0;
        List<int[]> list = new ArrayList<>();

        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] move = queue.poll();
            int currentX = move[0];
            int currentY = move[1];
            // 해당 좌표 인구를 더해준다.
            sum += map[currentX][currentY];
            // 해당 좌표의 위치를 넣어둔다.
            list.add(new int[]{currentX, currentY});

            for (int i = 0; i < dx.length; i++) {
                int nextX = currentX + dx[i];
                int nextY = currentY + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N && !visited[nextX][nextY]) {
                    int dif = Math.abs(map[currentX][currentY] - map[nextX][nextY]);
                    // 국경선을 공유한 좌표를 반복을 할 수 있도록 큐에 넣어주고 국경선 공유 표시를 해준다.
                    if (L <= dif && dif <= R) {
                        visited[nextX][nextY] = true;
                        queue.offer(new int[]{nextX, nextY});
                        flag = 1;
                    }
                }
            }
        }
        
        // 각 칸의 인구수를 구해준다.
        div = sum / list.size();

        // 구한 각 칸의 인구수를 분배해준다.
        for (int i = 0; i < list.size(); i++) {
            int[] coor = list.get(i);
            map[coor[0]][coor[1]] = div;
        }

        return flag;
    }
}