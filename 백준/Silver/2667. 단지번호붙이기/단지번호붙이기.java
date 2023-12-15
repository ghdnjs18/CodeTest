import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bufferedReader.readLine());

        // 지도 만들기
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] site = bufferedReader.readLine().split("");
            for (int j = 0; j < site.length; j++) {
                map[i][j] = Integer.parseInt(site[j]);
            }
        }
        bufferedReader.close();

        // 집 단지 헤아리기
        int house = 0;
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                if (map[i][j] == 1) {
                    cnt = houseSearch(map, i, j);
                    house++;
                    queue.offer(cnt);
                }
            }
        }
        bufferedWriter.write(house + "\n");

        while (!queue.isEmpty()) {
            bufferedWriter.write(queue.poll() + "\n");
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }
    
    public static int houseSearch(int[][] map, int row, int col) {
        int cnt = 0;
        if (row >= 0 && col >= 0 && row < map.length && col < map.length && map[row][col] == 1) {
            map[row][col] = 0;

            cnt += houseSearch(map, row + 1, col);
            cnt += houseSearch(map, row - 1, col);
            cnt += houseSearch(map, row, col + 1);
            cnt += houseSearch(map, row, col - 1);

            return cnt + 1;
        }
        return 0;
    }
}