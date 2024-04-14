import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 테스트 케이스 개수 T, 선물의 가격 p, 친구의 수 N, 한도 금액 A
    * 조건 : 각 친구들은 한도 금액보다 많은 비용을 내지 못한다, 친구들 간의 비용의 차이를 최소로 해야한다.
    * 비용처리하는 경우의 수가 같을 경우, 앞에 사람이 돈을 더 많이 낸다.
    * 목표 : 각 테스트 케이스마다 각 친구들의 비용 출력, 불가능한경우 IMPOSSIBLE
    * 한도 금액을 입력 받으면서 총 금액을 계산하고 총 금액이 선물 가격보다 적으면 불가능하고 동일하다면 전원의 금액을 사용해야 한다.
    * 친구 전원이 선물의 1/n가격을 동일하게 지출을 하고 소모된 비용만큼 선물의 가격을 감소시킨다.
    * 친구들이 소비한 비용을 한도 금액을 기준으로 내림차순으로 정렬해준다.
    * 남은 선물 가격을 친구들이 돌아가며 감소시킨다.
    * 다시 소비한 비용을 원래 위치대로 정렬을 해주고 출력을 해준다.
    * */

    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        // 테스트 케이스의 수
        int T = readNumber();

        for (int i = 0; i < T; i++) {
            // 선물의 가격
            int P = readNumber();
            // 친구의 수
            int N = readNumber();

            // 친구들의 한도 금액
            int total = 0;
            int[] moneys = new int[N];
            for (int j = 0; j < N; j++) {
                moneys[j] = readNumber();
                total += moneys[j];
            }

            // 친구 전원의 금액 합이 선물 가격보다 적으면 살 수 없다.
            if (total < P) {
                stringBuilder.append("IMPOSSIBLE").append("\n");
                continue;
            } else if (total == P) {
                for (int money : moneys) {
                    stringBuilder.append(money).append(" ");
                }
                stringBuilder.append("\n");
                continue;
            }

            // 선물의 1/n 금액
            int fairCost = P / N;

            // 기본 금액 계산
            int[][] costs = new int[N][2];
            for (int j = 0; j < N; j++) {
                // 현재 사람이 가진 돈과 기본 금액 중 작은 값을 선택하여 부담 금액으로 설정
                costs[j][0] = Math.min(fairCost, moneys[j]);
                // 기존의 위치 정보
                costs[j][1] = j;

                // 선물 비용에서 현재 사람의 부담 금액을 빼줌
                P -= costs[j][0];
            }

            // 돈을 많이 가지고 있는 순으로 정렬
            Arrays.sort(costs, (o1, o2) -> moneys[o2[1]] - moneys[o1[1]]);

            // 남은 선물 비용을 금액이 많은 사람부터 추가 누적
            while (P > 0) {
                for (int j = 0; j < N; j++) {
                    if (moneys[costs[j][1]] > costs[j][0]) {
                        costs[j][0]++;
                        P--;
                    }

                    if (P == 0) break;
                }
            }

            // 다시 위치순으로 정렬
            Arrays.sort(costs, (o1, o2) -> o1[1] - o2[1]);

            for (int[] cost : costs) {
                stringBuilder.append(cost[0]).append(" ");
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder);
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