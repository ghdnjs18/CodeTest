import java.io.*;

public class Main {
    /**
     * 주어진 값 : 9X9크기의 임의의 숫자
     * 조건 : 동일한 세로 줄, 가로 줄, 3x3크기에 동일한 숫자는 들어오지 않는다.
     * 목표 : 사전식으로 가장 빠른 조건이 성립하는 스도쿠의 답
     * 조건별로 배열을 만들어 해당 숫자가 해당 위치에 있는지를 확인하고 해당 숫자를 넣을 수 있다면 체크하고 반복을 한다.
     * 낮은 번호부터 넣어가면서 탐새을 하고 실패하면 성공지점으로 와서 다음 수를 넣어가면 체크한다. (사전순 먼저 시도 - 백트래킹)
     * -> 재귀함수에서 그냥 리턴으로 끝내면 다시 초기화를 시키므로 바로 시스템은 종료해줘야한다.
     */

    static int[][] map;
    static boolean[][] row;
    static boolean[][] col;
    static boolean[][] square;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        
        // 현재 보드의 상태 입력
        map = new int[9][9];
        row = new boolean[9][9];
        col = new boolean[9][9];
        square = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            char[] charArray = bufferedReader.readLine().toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                map[i][j] = charArray[j] - '0';

                if (map[i][j] != 0) {
                    row[map[i][j] - 1][i] = true;
                    col[map[i][j] - 1][j] = true;
                    square[map[i][j] - 1][(i / 3) * 3 + (j / 3)] = true;
                }
            }
        }
        
        // 보드에 있는 숫자를 파악한다.
        findNumber(0);
    }

    private static void findNumber(int cnt) {
        int x = cnt / 9;
        int y = cnt % 9;
        int s = (x / 3) * 3 + (y / 3);

        if (cnt == 81) {
            for (int[] row : map) {
                for (int col : row) {
                    System.out.print(col);
                }
                System.out.println();
            }
            System.exit(0);
        }

        if (map[x][y] == 0) {
            for (int i = 0; i < 9; i++) {
                if (!row[i][x] && !col[i][y] && !square[i][s]) {
                    row[i][x] = true;
                    col[i][y] = true;
                    square[i][s] = true;
                    map[x][y] = i + 1;
                    findNumber(cnt + 1);
                    
                    row[i][x] = false;
                    col[i][y] = false;
                    square[i][s] = false;
                    map[x][y] = 0;
                }
            }
        } else {
            findNumber(cnt + 1);
        }
    }
}