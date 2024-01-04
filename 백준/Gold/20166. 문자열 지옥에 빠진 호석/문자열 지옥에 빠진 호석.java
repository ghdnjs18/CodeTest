import java.io.*;
import java.util.*;

public class Main {

    static String[][] map;
    static String[] godStr;
    static Map<String, Integer> godCases = new HashMap<>();
    static int N, M, K;
    static int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1};
    static int[] dy = {0, 0, -1, 1, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        // 주어진 격자공간을 이차배열로 만들어준다.
        map = new String[N][M];
        for (int i = 0; i < N; i++) {
            String[] array = bufferedReader.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = array[j];
            }
        }
        
        // 신이 좋아하는 문자열을 만들 수 있는 경우의 수를 확인하기 위해 map 사용
        godStr = new String[K];
        for (int i = 0; i < K; i++) {
            godStr[i] = bufferedReader.readLine();
            godCases.put(godStr[i], 0);
        }
        bufferedReader.close();

        // 위치별로 움직임 반복
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, map[i][j]);
            }
        }

        // 경우의 수 출력
        for (String str : godStr) {
            System.out.println(godCases.get(str));
        }
    }

    public static void dfs(int x, int y, String str) {
        // 문자열에 포함이 되어 있으면 경우의 수 체크
        if (godCases.containsKey(str)) {
            godCases.put(str, godCases.get(str) + 1);
        }

        // 문자열 크기에 도착하면 종료
        if (str.length() == godStr[0].length()) {
            return;
        }

        // 상하좌우 대각선을 반복하면서 이동한다.
        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0) nextX = N - 1;
            if (nextY < 0) nextY = M - 1;
            if (nextX == N) nextX = 0;
            if (nextY == M) nextY = 0;

            dfs(nextX, nextY, str + map[nextX][nextY]);
        }
    }
}