import java.io.IOException;

public class Main {

    /*
    * 주어진 값 : 학생의 수 N, 나이 순으로 정렬된 학생의 점수 N개
    * 목표 : 연속되는 수들의 차이의 최댓값
    * 나이 차이가 많이 날 경우에 부정적이다 -> 주어진 값을 따로 정렬하면 안된다.
    * 연속으로 붙은 숫자들 간에 차이가 많이 나도록 묶어줘야한다.
    * 주어진 값은 학생의 점수 하나 해당 값을 추가하는 식으로 비교해서 최댓값을 누적 시킨다.
    * */

    public static void main(String[] args) throws IOException {
        // 학생 수
        int N = readNumber();

        // 학생 정보 입력
        int[] students = new int[N];
        for (int i = 0; i < N; i++) {
            students[i] = readNumber();
        }

        int[] dp = new int[N + 1];
        for (int i = 0; i < N; i++) {
            // 해당 수의 최소, 최대는 자신
            int max = students[i];
            int min = students[i];
            // 해당수 부터 이전 값까지 비교 한다.
            for (int j = i; j >= 0; j--) {
                max = Math.max(max, students[j]);
                min = Math.min(min, students[j]);
                dp[i + 1] = Math.max(dp[i + 1], dp[j] + max - min);
            }
        }

        System.out.println(dp[N]);
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