import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 각각의 아파트 단지의 위치와 학생 수(N개), 학교의 위치 S, 통학 버스의 정원 K
    * 조건 : 통학 버스는 학교에서 출발한다.
    * 목표 : 모든 학생이 등교하는 통학 버스의 최소 이동거리
    * 학교를 기준으로 왼쪽과 오른쪽에 있는 아파트를 나눠 배열로 만든다음 학교에서 아파트의 왕복하는 거리와 학생 수를 입력해준다.
    * 학교에서 아파트 왕복하는 거리를 기준으로 내림차순 정렬을 해서 멀리는 거리부터 버스를 이동한다.
    * 버스를 운행하면서 가장 먼 거리를 체크하고 학생을 정원 만큼 태우고 거리를 누적 시킨다.
    * -> 정원 보다 탑승객이 적은 경우 먼거리 부터 시작하기 때문에 처음 거리에 학생을 누적시켜 탑승한다.
    * -> 정원과 탑승객이 일치 할 경우 거리를 더해주고 버스를 초기화 해준다.
    * -> 정원 보다 탑승객이 많은 경우 이전 아파트 거리를 더해주고 학교에 갔다가 다시 온다
    * 왼쪽 오른쪽 반복을 해주고 거리를 출력한다.
    * -> 한 아파트에 여러번 버스를 가야할 수도 있는걸 고려안함.
    * -> 정원 보다 탑승객이 많은 경우 아파트에 추가로 와야하는 횟수를 구해 2번 이상인 경우 와야하는 거리를 추가로 더해준다.
    * */

    static int N, K, result = 0;

    public static void main(String[] args) throws IOException {
        // 아파트 단지의 수
        int N = readNumber();
        // 통학 버스의 정원
        K = readNumber();
        // 학교의 위치
        int S = readNumber();

        // 각 아파트 단지의 위치와 핵생 수 입력
        int[][] leftApt = new int[N][2];
        int[][] rightApt = new int[N][2];
        for (int i = 0; i < N; i++) {
            int location = readNumber();
            int student = readNumber();
            if (location < S) {
                leftApt[i][0] = (S - location) * 2;
                leftApt[i][1] = student;
                rightApt[i][0] = 0;
                rightApt[i][1] = 0;
            } else {
                leftApt[i][0] = 0;
                leftApt[i][1] = 0;
                rightApt[i][0] = (location - S) * 2;
                rightApt[i][1] = student;
            }
        }
        
        // 거리를 기준으로 내림차순 
        Arrays.sort(leftApt, (o1, o2) -> o2[0] - o1[0]);
        Arrays.sort(rightApt, (o1, o2) -> o2[0] - o1[0]);

        // 버스 이동
        startBus(leftApt);
        startBus(rightApt);

        System.out.println(result);
    }

    private static void startBus(int[][] apts) {
        int lengh = 0;
        int passenger = 0;
        for (int[] apt : apts) {
            if (apt[0] == 0) break;
            // 정원 보다 탑승객이 적은 경우 누적시켜 탑승한다.
            if (K > passenger + apt[1]) {
                if (passenger == 0) lengh = apt[0];

                passenger += apt[1];
            // 정원과 탑승객이 일치 할 경우 거리를 더해주고 버스를 초기화 해준다.
            } else if (K == passenger + apt[1]) {
                if (passenger == 0) result += apt[0];
                else result += lengh;

                passenger = 0;
                lengh = 0;
            // 정원 보다 탑승객이 많은 경우 학교에 갔다가 다시 온다.
            } else if (K < passenger + apt[1]) {
                if (passenger == 0) result += apt[0];
                else result += lengh;

                // 추가 탑승 횟수
                int cnt = (apt[1] + passenger - 1) / K;

                // 2번 이상 추가 탑승 경우 미리 거리를 더해준다.
                if (cnt > 1) {
                    result += apt[0] * (cnt - 1);
                }

                passenger = passenger + apt[1] - K * cnt;
                lengh = apt[0];
            }
        }
        // 마지막 학교 복귀
        result += lengh;
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