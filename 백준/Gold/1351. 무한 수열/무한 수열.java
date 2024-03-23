import java.io.*;
import java.util.*;

public class Main {

    /*
    * 주어진 값 : 정수 N, P, Q, 무한 수열의 수식 A_0 = 1, A_i = A_[i/P] + A_[i/Q] (i ≥ 1)
    * 목표 : A_N의 값
    * 반복하는 과정이 중복되어 DP를 사용해서 중복을 처리한다.
    * N의 범위가 int의 범위를 넘어서 배열을 사용하지 못한다. -> Map을 이용해서 대체해서 사용한다.
    * 타뷸레이션 방식을 사용하기엔 반복이 많으므로 메모이제이션 방식을 사용한다.
    * */

    static long N, P, Q;
    static Map<Long, Long> map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        // 입력값
        N = Long.parseLong(stringTokenizer.nextToken());
        P = Long.parseLong(stringTokenizer.nextToken());
        Q = Long.parseLong(stringTokenizer.nextToken());

        // 무한 수열 반복
        map = new HashMap<>();
        map.put(0L, 1L);
        map.put(1L, 2L);

        System.out.println(infiniteSequence(N));
    }

    private static long infiniteSequence(long n) {
        if (map.containsKey(n)) return map.get(n);
        long one = infiniteSequence(n/P);
        long two = infiniteSequence(n/Q);
        map.put(n, one + two);
        return one + two;
    }
}