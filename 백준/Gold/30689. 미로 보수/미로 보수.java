import java.io.*;
import java.util.*;

public class Main {
    /*
     * 주어진 미로 정보를 이차배열에 저장한다.
     * 움직임의 조건은 미로 정보에 따라 상하좌우로 움직일 수 있다.
     * 각 칸에서 가능성을 확인해야 하기 때문에 배열 전체를 깊이 탐색으로 반복하면서 중복 칸수를 확인한다.
     * 2번 도착했을 경우 반복되는 장소이며, 2번 움직인 장소들의 보수 비용을 list로 체크를 해주고 다음 번 확인에 중복되지 않게 고친 곳은 움직임을 1로 변경해준다.
     * 반복되는 구간의 list에서 보수 비용의 최소 값들을 더해 비용을 구한다.
     * */

    static int N, M;
    static char[][] map;
    static int[][] cost;
    static int[][] count;
    static boolean[][] visited;
    static List<Integer> checkCost = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new char[N][M];
        cost = new int[N][M];
        count = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                cost[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        bufferedReader.close();

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) searchMiro(i, j);
                if (!checkCost.isEmpty()) answer += updateMiro();
            }
        }

        System.out.println(answer);
    }

    private static void searchMiro(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M || count[x][y] == 2) {
            return ;
        }

        if (!visited[x][y]) {
            count[x][y] += 1;
            switch (map[x][y]) {
                case 'U':
                    searchMiro(x - 1, y);
                    break;
                case 'D':
                    searchMiro(x + 1, y);
                    break;
                case 'L':
                    searchMiro(x, y - 1);
                    break;
                case 'R':
                    searchMiro(x, y + 1);
                    break;
            }
            visited[x][y] = true;

            if (count[x][y] == 2) {
                checkCost.add(cost[x][y]);
                count[x][y] = 1;
            }
        }
    }

    private static int updateMiro() {
        int min = checkCost.stream().min(Integer::compare).get();
        checkCost.clear();

        return min;
    }
}
