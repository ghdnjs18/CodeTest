import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : N개의 카드 묶음의 크기
    * 목표 : 최소 비교 횟수
    * 조건 : 비교 횟수는 카드 묶음 2개의 합이다.
    * 카드 묶음의 비교를 최소로 하기 위해서는 작은 카드 묶음부터 합쳐야한다.
    * 카드 묶음을 합치고 합친 카드 묶음의 크기를 기존의 카드 묶음들과 정렬한 후 가장 작은 카드 묶음을 비교해야한다.
    * 변화하는 기준에서 자동으로 정렬을 할 수 있는 우선순위큐를 이용하면 된다
    * */
    public static void main(String[] args) throws IOException {
        // 카드 묶음의 크기
        int N = readNumber();

        // 카드 묶음의 크기 입력
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            queue.offer(readNumber());
        }

        // 카드 묶음의 비교값 더하기
        int sum = 0;
        while (queue.size() > 1) {
            int firstCard = queue.poll();
            int secondCard = queue.poll();

            sum += firstCard + secondCard;
            queue.offer(firstCard + secondCard);
        }

        // 출력
        System.out.println(sum);
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