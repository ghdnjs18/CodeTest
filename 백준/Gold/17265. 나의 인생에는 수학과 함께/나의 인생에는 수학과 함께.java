import java.io.*;
import java.util.*;

public class Main {
    /*
    * (1, 1)에서 (N, N)로 진행하는 최단거리에서는 밑과 오른쪽으로만 움직이면 된다. (그래프 탐색)
    * -> 모두 진행해서 최댓값과 최솟값을 구해야한다. (브루트포스 알고리즘)
    * 최대 5x5에서 251번이면 모든 경우의 수를 한다.
    * -> 경우의 수가 더많아 진다면 중복으로 사용되는 부분을 처리하는 로직을 추가해야 할 것이다. (다이나믹 프로그래밍)
    * 입력값을 숫자와 기호가 있어 배열을 2개 사용? -> char를 이용해 계산이 가능하다. '1' - '0' = 1
    * 기호 일경우에 다음 숫자를 찾아 계산을 하면서 도착지점에 도착하면 최댓값과 최솟값을 비교하여 변경해준다.
    * */

    static int N, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 지도의 크기
        N = Integer.parseInt(bufferedReader.readLine());

        // 지도 입력
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = stringTokenizer.nextToken().charAt(0);
            }
        }

        // 경로 이동
        move(0, 0, map[0][0] - '0');

        System.out.println(max);
        System.out.println(min);
    }

    private static void move(int x, int y, int num) {
        int[] dx = {1, 0};
        int[] dy = {0, 1};

        if (x == N - 1 && y == N - 1) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < dx.length; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < N && nextY < N) {
                int nextNum = nextNum(num, map[nextX][nextY], map[x][y]);
                move(nextX, nextY, nextNum);
            }
        }
    }

    private static int nextNum(int num, char nextNum, char oper) {
        switch (oper) {
            case '+':
                num += nextNum - '0';
                break;
            case '-':
                num -= nextNum - '0';
                break;
            case '*':
                num *= nextNum - '0';
                break;
        }

        return num;
    }
}