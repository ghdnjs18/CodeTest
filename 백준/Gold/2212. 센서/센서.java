import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 센서의 개수 N, 집중국의 개수 K, 센서의 위치 N개
    * 목표 : 각 집중국에서 센서의 거리의 합의 최솟값
    * 센서의 위치가 중복이 가능한데, 입력값에서 위치가 다르게 나와 파악하기 힘들고 위치를 변경해도 조건에 영향을 주지않아 정렬을 한다.
    * 집중국을 어느 센서에 설치했을 때, 최소 값이 나올 수 있는지를 확인해야 하므로 센서간의 거리들을 구한다.
    * 집중국은 2개가 붙어 있어야 서로의 센서를 사용안한다. -> 센서간의 거리가 큰 곳부터 집중국을 설치한다.
    * */

    public static void main(String[] args) throws IOException {
        // 센서의 개수
        int N = readNumber();
        // 집중국의 개수
        int K = readNumber();

        // 센서의 위치 입력
        int[] sensors = new int[N];
        for (int i = 0; i < N; i++) {
            sensors[i] = readNumber();
        }

        // 센서 위치 정렬
        Arrays.sort(sensors);

        // 센서 간의 거리 구하기
        int[] dif = new int[N - 1];
        for (int i = 0; i < N-1; i++) {
            dif[i] = sensors[i + 1] - sensors[i];
        }

        // 센서 간의 거리 정렬
        Arrays.sort(dif);

        // 집중국이 설치된 수만큼 센서를 뺀 거리들을 다 더해준다.
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