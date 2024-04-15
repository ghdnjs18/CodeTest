import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : N개의 문제의 데드라인, 문제 풀었을때 주는 컵라면 수
    * 조건 : 문제를 푸는 데 걸리는 시간은 1이다.
    * 목표 : 문제를 풀어 받을 수 있는 최대 컵라면 수 구하기
    * 데드라인이 빠른 순부터 동일한 데드라인의 컵라면의 수를 비교해 많이 주는 문제의 컵라면 수를 누적시킨다.
    * 단, 데드라인이 느려도 앞의 데드라인보다 컵라면을 많이 준다면 해당 문제를 풀어야한다.
    * -> 데드라인이 느린 문제부터 계산을 하면서 컵라면을 누적시키면서 사용한 문제는 제외해준다.
    * 해당 데드라인때마다 가장 많은 컵라면로 정렬을 해야한댜 -> 우선순위 큐 활용
    * -> 우선순위 큐를 사용하는데 2가지 값을 같이 비교하기 위해서는 클래스로 정렬을 정의해줘야한다.
    * */

    public static void main(String[] args) throws IOException {
        // 문제의 수
        int N = readNumber();

        // 문제 정보 입력
        Problem[] problems = new Problem[N];
        for (int i = 0; i < N; i++) {
            int deadline = readNumber();
            int cupnoodle = readNumber();

            problems[i] = new Problem(deadline, cupnoodle);
        }
        // 데드라인이 느린 순으로 정렬
        Arrays.sort(problems, (o1, o2) -> o2.deadline - o1.deadline);

        int cnt = 0;
        int sum = 0;
        Queue<Problem> queue = new PriorityQueue<>();
        // 느린 데드라인부터 컵라면을 누적시킨다.
        for (int i = N; i > 0; i--) {
            while (cnt < N && problems[cnt].deadline == i) {
                queue.offer(problems[cnt++]);
            }

            if (!queue.isEmpty()) sum += queue.poll().cupnoodle;
        }

        System.out.println(sum);
    }

    static class Problem implements Comparable<Problem> {
        int deadline;
        int cupnoodle;

        public Problem(int deadline, int cupnoodle) {
            this.deadline = deadline;
            this.cupnoodle = cupnoodle;
        }

        @Override
        public int compareTo(Problem o) {
            return (cupnoodle != o.cupnoodle) ?
                    o.cupnoodle - cupnoodle : (deadline != o.deadline) ?
                        deadline - o.deadline : 0;
        }
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        return flag ? -cur : cur;
    }
}