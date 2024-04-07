import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 원생의 수 N, 조의 수 K, 원생들의 키 N개 (오름차순)
    * 목표 : 티셔츠 만드는 비용 최소 (티셔츠 비용 = 같은 조의 가장 큰 키와 가장 작은 키의 차이)
    * 원생의 수가 300,000으로 N^2은 시간 초과가 날 것이다. -> 탐욕법, 동적계획법
    * 키가 오름차순으로 주어지기 때문에 키차이의 최소값들을 붙어있는 인원과 나올 수 있다.
    * 키차이로 N - 1개의 티셔츠 비용을 알 수 있다.
    * 조의 수로 원생의 수를 나누는 기준이므로 K개의 조로 나누기 위해서는 N - K개의 티셔츠 비용을 뽑는 것과 같다.
    * 티셔츠 비용이 적은 N - K개를 더해 출력한다.
    * */
    public static void main(String[] args) throws IOException {
        // 원생의 수
        int N = readNumber();
        // 조의 수
        int K = readNumber();

        // 원생의 키정보 입력
        int[] student = new int[N];
        for (int i = 0; i < N; i++) {
            student[i] = readNumber();
        }

        // 원생의 키 차이 입력
        int[] dif = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            dif[i] = student[i + 1] - student[i];
        }

        // 키 차이를 정렬
        Arrays.sort(dif);

        // 낮은 키 차이로 조를 나눈다.
        int result = 0;
        for (int i = 0; i < N - K; i++) {
            result += dif[i];
        }

        System.out.println(result);
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