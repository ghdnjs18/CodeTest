import java.io.*;
import java.util.*;

public class Main {

    /*
    * 주어진 값 : 해야할 일 수 N, 일을 처리하는 데 걸리는 시간 T, 마감 시간 S
    * 목표 : 최대한 늦게 일을 시작할 수 있는 시간
    * 마감 시간 - 걸리는 시간으로 시작해야 하는 시간을 구할 수 있다.
    * 늦게 일을 할 수 있는 시간을 알기위해 마감시간이 높은 순으로 정렬을 해준다.
    * 일을 끝마칠 수 있다면 시작 시간이 가장 빠른 시간이 가장 늦게 일어날 수 있는 시간이다.
    * */

    public static void main(String[] args) throws IOException {
        // 일의 수
        int N = readNumber();

        // 시간 입력
        int[][] times = new int[N][2];
        for (int i = 0; i < N; i++) {
            times[i][0] = readNumber();
            times[i][1] = readNumber();
        }

        // 마감시간을 기준으로 내림차순으로 정렬한다.
        Arrays.sort(times, (o1, o2) -> o2[1] - o1[1]);

        int temp = 0;
        int start = times[0][1] - times[0][0];
        for (int i = 1; i < N; i++) {
            // 마감 시간이 이전 일의 시작 시간 보다 늦으면 겹치는 시간 확인
            if (times[i][1] >= start) {
                temp += times[i][1] - start;
            // 마감 시간이 이전 일의 시작 시간 보다 빠르면 여유 시간 확인
            } else if (start - times[i][1] >= temp){
                temp = 0;
            } else if (start - times[i][1] < temp) {
                temp -= start - times[i][1];
            }

            start = times[i][1] - times[i][0];
        }
        // 남은 겹치는 시간이 있다면 시작 시간을 앞당긴다.
        if (temp > 0) {
            start -= temp;
        }

        System.out.println(start < 0 ? -1 : start);
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