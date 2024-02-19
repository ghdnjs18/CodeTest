import java.io.*;
import java.util.*;

public class Main {
    
    static boolean[] visited = new boolean[61];
    static int[][] times = new int[61][5];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int N = Integer.parseInt(bufferedReader.readLine());

        buttonCaseFind();
        for (int i = 0; i < N; i++) {
            int time = Integer.parseInt(bufferedReader.readLine());
            int hour = time / 60;
            int min = time % 60;
            for (int j = 1; j < 6; j++) {
                if (j == 1) {
                    stringBuilder.append(times[min][j] + hour).append(" ");
                } else {
                    stringBuilder.append(times[min][j]).append(" ");
                }
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static void buttonCaseFind() {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0, 0, 0, 0});
        while (!queue.isEmpty()) {
            int[] time = queue.poll();
            int currentTime = time[0];

            if (currentTime >= 0 && currentTime <= 60 && !visited[currentTime]) {
                visited[currentTime] = true;
                times[currentTime] = time;
                queue.offer(new int[]{currentTime - 1, time[1], time[2], time[3], time[4], time[5] + 1});
                queue.offer(new int[]{currentTime + 1, time[1], time[2], time[3], time[4] + 1, time[5]});
                queue.offer(new int[]{currentTime - 10, time[1], time[2], time[3] + 1, time[4], time[5]});
                queue.offer(new int[]{currentTime + 10, time[1], time[2] + 1, time[3], time[4], time[5]});
                queue.offer(new int[]{currentTime + 60, time[1] + 1, time[2], time[3], time[4], time[5]});
            }
        }
    }
}