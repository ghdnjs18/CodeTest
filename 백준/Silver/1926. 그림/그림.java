import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        // 입력값으로 문제에서 주어진 도화지를 표현한다.
        String[][] map = new String[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = bufferedReader.readLine().split(" ");
        }
        bufferedReader.close();

        // 그림이 그려진 부분 확인용
        boolean[][] visited = new boolean[n][m];
        // 그림을 확인하기 위한 좌표 넣기용
        Queue<int[]> queue = new LinkedList<>();
        int picture = 0; // 그림의 개수
        int area = 0; // 가장 넒은 그림의 넓이
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int cnt = 0; // 그림의 넓이 확인용
                if (map[i][j].equals("1") && !visited[i][j]) {
                    picture++;
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] temp = queue.poll();
                        int x = temp[0];
                        int y = temp[1];

                        if (x >= 0 && y >= 0 && x < n && y < m && map[x][y].equals("1") && !visited[x][y]) {
                            visited[x][y] = true;

                            // 상하좌우 움직임
                            for (int k = 0; k < 4; k++) {
                                queue.offer(new int[]{x + dx[k], y + dy[k]});
                            }
                            cnt++;
                        }
                    }
                }
                // 가장 넓은 그림 확인하기
                area = Math.max(area, cnt);
            }
        }
        System.out.println(picture);
        System.out.println(area);
    }
}