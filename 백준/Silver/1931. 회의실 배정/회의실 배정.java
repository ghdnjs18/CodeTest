import java.io.*;
import java.util.*;

public class Main {
    /*
    * 시작하는 시간과 끝나는 시간을 묶어서 사용한다.
    * 끝나는 시간으로 먼저 오름차순 정렬하고 같을 경우 시작하는 시간으로 오름차순 정렬한다.
    * 끝나는 시간을 기준으로 시작 시간을 비교하면서 회의의 수를 헤아린다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int startTime = Integer.parseInt(stringTokenizer.nextToken());
            int endTime = Integer.parseInt(stringTokenizer.nextToken());

            queue.offer(new int[]{startTime, endTime});
        }

        Queue<Integer> currentTime = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // 비교 하기 위해서 임시로 넣는다.
        currentTime.offer(0);
        while (!queue.isEmpty()) {
            int[] time = queue.poll();
            int startTime = time[0];
            int endTime = time[1];

            // 종료시간이 가장 빠른 회의의 시작 시간이 작으면 추가한다.
            if (currentTime.peek() <= startTime) {
                currentTime.offer(endTime);
            }
        }
        // 임의로 넣은 0을 빼고 수를 헤아린다.
        System.out.println(currentTime.size() - 1);
    }
}