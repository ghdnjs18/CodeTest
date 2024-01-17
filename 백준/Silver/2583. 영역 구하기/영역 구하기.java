import java.io.*;
import java.util.*;

public class Main {
    /*
    * 첫줄의 입력 값을 통해 배열의 크기와 반복 횟수를 이용할 수 있다.
    * 좌표값 꼭지점을 인덱스로 생각하면 위치를 파악하기 편하다. (단, 모양이 예시 형태의 대칭으로 나온다.)
    * 대칭 형태라도 모양을 보는 게 아닌 크기를 보기 때문에 상관없다.
    * 동일한 위치를 중복해서 표시를 해도 한 번 사용한 것과 같기 때문에 중복 위치는 표시를 안하고 넘어가도 된다.
    * 입력을 이용해서 색칠하는 로직과 색칠 안된 부분을 헤아리는 로직 2개가 필요하다.
    * 색칠하는 부분은 이중 for문으로 색칠 안된 부분은 DFS, BFS를 사용하면 될 것 같다.
    * */
    static boolean[][] visited;
    static int M, N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        M = Integer.parseInt(stringTokenizer.nextToken());
        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        visited = new boolean[M][N];
        for (int i = 0; i < K; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int startY = Integer.parseInt(stringTokenizer.nextToken());
            int startX = Integer.parseInt(stringTokenizer.nextToken());
            int endY = Integer.parseInt(stringTokenizer.nextToken());
            int endX = Integer.parseInt(stringTokenizer.nextToken());
            for (int j = startX; j < endX; j++) {
                for (int k = startY; k < endY; k++) {
                    if (!visited[j][k]) {
                        visited[j][k] = true;
                    }
                }
            }
        }


        int cnt = 0;
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    cnt++;
                    visited[i][j] = true;
                    // DFS 방식
                    // 움직이는 횟수를 헤아리고 나오기 떄문에 현재 위치를 더해줘야한다.
//                    answer.add(DFS(i, j) + 1);
                    // 재귀가 아니기 때문에 함수안에서 현재 위치를 더하고 올 수 있음.
                    answer.add(BFS(i, j));

                }
            }
        }

        // 넓이를 오름차순으로 정렬
        answer.sort((o1, o2) -> o1 -o2);
        System.out.println(cnt);
        for (int i : answer) {
            System.out.print(i + " ");
        }
    }

    public static int DFS(int x, int y) {
        int cnt = 0;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX >= 0 && nextY >= 0 && nextX < M && nextY < N && !visited[nextX][nextY]) {
                visited[nextX][nextY] = true;

                // 다음으로 넘어 갈 때 0으로 가고 끝나고 1을 더할 수 있도록 증가를 뒤에 나둔다.
                cnt += DFS(nextX, nextY);
                cnt++;
            }
        }
        return cnt;
    }

    public static int BFS(int x, int y) {
        int cnt = 1;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            x = temp[0];
            y = temp[1];

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX < M && nextY < N && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;

                    queue.offer(new int[]{nextX, nextY});
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
