import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : NxM의 배열 크기, 배열의 값 0 또는 1,
    * 목표 : 1끼리 연결된 모양의 최대 크기
    * 조건 : 0을 한번 1로 만들 수 있다.
    * 반복문을 이용해 0일 경우 1로 변경하고 깊이 탐색을 이용해 인접한 배열의 크기를 구한다.
    * 이후 변경 했던 배열의 값을 다시 0으로 만들고 반복하면서 최대 크기를 구한다.
    * -> 깊이 탐색시 시간 초과
    * -> 넓이 탐색으로 시간 단축 시도 : 크기 탐색에서의 문제가 아님
    * -> 0을 하나씩 바꾸면서 찾는 부분의 문제일 가능성 있음 : 전체를 탐색하며 변경하지 말고 입력시 0을 체크하고 0만 반복하도록 수정
    * -> 0을 변경할 필요없이 그냥 0의 위치에서 탐색을 시키면 변경을 안시키고도 원래 크기에 포함됨.
    * -> 0의 반복도 줄이고 dp를 활용한 방식으로 최대한 반복을 줄인다.
    * -> 먼저 그래프 탐색을 이용해서 기존에 있는 모양들의 크기와 모양별 번호를 부여하고 체크한 0에 인접한 확인해둔 모양들의 크기를 더한다.
    * */

    static int N, M, index = 1;
    static int[][] map;
    static int[][] mapIndex;
    static boolean[][] visited;
    static Map<Integer, Integer> indexSize = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // 배열의 크기
        N = readNumber();
        M = readNumber();

        // 배열 입력
        map = new int[N][M];
        mapIndex = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = readNumber();
                map[i][j] = num;
                if (num == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // 기존의 존재하는 모양 확인
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
//                    indexSize.put(index, getArraySizeBFS(i, j));
                    indexSize.put(index, getArraySizeDFS(i, j));
                    index++;
                }
            }
        }

        // 0의 위치에서 반복
        int max = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            max = Math.max(max, mergeArraySizeBFS(x, y));
        }

        // 크기 출력
        System.out.println(max);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        return flag ? -cur : cur;
    }

    private static int getArraySizeDFS(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int cnt = 1;
        visited[x][y] = true;
        mapIndex[x][y] = index;
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isValid(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] == 1) {
                cnt += getArraySizeDFS(nextX, nextY);
            }
        }

        return cnt;
    }

    private static int getArraySizeBFS(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        mapIndex[x][y] = index;
        queue.offer(new int[]{x, y});
        int cnt = 0;
        while (!queue.isEmpty()) {
            cnt++;
            int[] cur = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nextX = cur[0] + dx[i];
                int nextY = cur[1] + dy[i];

                if (isValid(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] == 1) {
                    visited[nextX][nextY] = true;
                    mapIndex[nextX][nextY] = index;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }

        return cnt;
    }

    private static int mergeArraySizeBFS(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> findSizeQueue = new LinkedList<>();
        Set<Integer> checkArea = new HashSet<>();
        findSizeQueue.offer(new int[]{x, y});
        int cnt = 1;
        while (!findSizeQueue.isEmpty()) {
            int[] cur = findSizeQueue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nextX = cur[0] + dx[i];
                int nextY = cur[1] + dy[i];

                if (isValid(nextX, nextY) && mapIndex[nextX][nextY] != 0) {
                    checkArea.add(mapIndex[nextX][nextY]);
                }
            }
        }

        for (int index : checkArea) {
            cnt += indexSize.get(index);
        }

        return cnt;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}