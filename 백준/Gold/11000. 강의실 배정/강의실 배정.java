import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어지는 입력값들을 빠른 시간순으로 정렬을 하고 사용을 한다.
    * 시작시간이 빠른 순으로 정렬하고 동일하다면 종료시간이 빠른 순으로 정렬한다.
    * 정렬된 시간을 이용해서 현재 종료시간 보다 시작시간이 빠르면 새롭게 종료시간을 누적시켜 진행중인 강의의 수를 헤아린다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        // 시작시간이 빠른 순으로 정렬하고 동일하다면 종료시간이 빠른 순으로 정렬한다.
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int startTime = Integer.parseInt(stringTokenizer.nextToken());
            int endTime = Integer.parseInt(stringTokenizer.nextToken());
            queue.offer(new int[]{startTime, endTime});
        }

        // 현재 진행중인 가장 빠른 종료시간이 해당 강의의 시작 시간 보다 같거다 빠르면
        // 이미 종료 했기 때문에 해당 시간을 제외하고 해당 강의의 종료 시간을 넣어준다.
        Queue<Integer> currentTime = new PriorityQueue<>();
        currentTime.offer(0);
        while (!queue.isEmpty()) {
            int[] time = queue.poll();
            int startTime = time[0];
            int endTime = time[1];

            if (currentTime.peek() <= startTime) {
                currentTime.poll();
            }
            currentTime.offer(endTime);
        }

        System.out.println(currentTime.size());
    }
}