import java.io.*;
import java.util.*;

public class Main {
    /*
    * 시간이 증가하는 조건은 4가지다.
    * 1. 10초 증가, 2. 60초 증가, 3. 600초 증가, 4. 30초 증가(시작)
    * 30초 증가를 안했으면 시작버튼이 안눌렸으니 한번더 눌러줘야한다.
    * 조리 시간이 분 초로 주어지는데 계산을 편하게 할 수 있도록 초로 통일해준다.
    * 항상 주어진 목표가 0일 때를 까먹지 말자
    * */

    static boolean[] visited = new boolean[361];
    static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 분과 초를 나눠 각각 10초단위를 기준으로 목표시간을 계산한다.
        String[] times = bufferedReader.readLine().split(":");
        int minute = Integer.parseInt(times[0]) * 6;
        int second = Integer.parseInt(times[1]) / 10;
        time = minute + second;

        int answer = BFS();

        // 시작 시간이 0 초이면 버튼을 누를 필요가 없다.
        System.out.println(time == 0 ? 0 : answer);
    }

    public static int BFS() {
        // 10초 단위로 시간을 계산한다.
        int[] button = {1, 3, 6, 60};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int currentTime = temp[0];
            int currentCnt = temp[1];
            int start = temp[2];

            // 시작 버튼이 눌렸으면 그대로 출력하고 안 눌렸다면, 눌러야할 시간이 있으면 시작 버튼의 횟수를 추가 한다.
            if (time == currentTime) {
                if (start == 1) return currentCnt;
                return currentCnt + 1;
            }

            for (int i = 0; i < 4; i++) {
                int nextTime = currentTime + button[i];

                if (nextTime <= time && !visited[nextTime]) {
                    visited[nextTime] = true;
                    if (button[i] == 3) { // 시작 버튼을 누르면 확인
                        queue.offer(new int[]{nextTime, currentCnt + 1, 1});
                    } else {
                        queue.offer(new int[]{nextTime, currentCnt + 1, start});
                    }
                }
            }
        }

        return 0;
    }
}