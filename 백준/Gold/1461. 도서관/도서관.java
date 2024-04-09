import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 책의 개수 N, 한 번에 옮길 수 있는 책의 개수 M (N <= M <= 50), 책의 위치 N 개 (-10000 <= i <= 10000, i != 0)
    * 목표 : 0에 위치에 있는 책들을 원래 자리로 옮기는 최소 이동 수
    * 모든 책들은 0에서 시작하고 0이 원래 위치인 책은 없다.
    * 위치를 양수와 음수로 나눠서 이동을 하되 양수는 큰수로 음수는 작은수로 해서 절대값이 큰 값부터 이동을 했을 경우 이동 수치를 구한다.
    * 이후 가장 큰 이동 수치를 제외한 모든 이동의 값을 돌아오는 이동수를 더해 2배를 해준다.
    * */

    public static void main(String[] args) throws IOException {
        // 책의 개수
        int N = readNumber();
        // 한 번에 옮길 수 있는 책의 개수
        int M = readNumber();

        // 책의 원래 위치 입력
        List<Integer> leftBooks = new ArrayList<>();
        List<Integer> rightBooks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int book = readNumber();
            if (book > 0) {
                rightBooks.add(book);
            } else {
                leftBooks.add(book);
            }
        }

        leftBooks.sort((o1, o2) -> o1 - o2);
        rightBooks.sort((o1, o2) -> o2 - o1);

        int cnt = 0;
        int result = 0;
        int max = 0;
        // 양수 위치 책 이동 거리 구하기
        for (int rightBook : rightBooks) {
            if (cnt++ == 0) {
                result += rightBook * 2;
                max = Math.max(max, rightBook);
            }
            if (cnt == M) {
                cnt = 0;
            }
        }

        cnt = 0;
        // 음수 위치 책 이동 거리 구하기
        for (int leftBook : leftBooks) {
            if (cnt++ == 0) {
                result += Math.abs(leftBook) * 2;
                max = Math.max(max, Math.abs(leftBook));
            }
            if (cnt == M) {
                cnt = 0;
            }
        }

        System.out.println(result - max);
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