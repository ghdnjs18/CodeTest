import java.io.*;
import java.util.*;

public class Main {

    /*
    * 입력 값 : 물건의 수 N, 각 물건의 무게 W와 가치 V, 배낭의 최대 무게 K
    * 목표 : 배낭안의 물건들의 가치의 최댓값 구하기
    * 최대의 무게를 확인 하기위해서 타뷸레이션 방식을 사용해서 물건을 추가 하면서 가치를 누적시키면서 최대 가치를 구한다.
    * 모든 물건을 추가 했을 때, 최대 무게인 경우의 가치를 출력시킨다.
    *
    * */

    static int N, K;
    static List<Cargo> cargos = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 물품의 수
        N = readNumber();
        // 넣을 수 있는 최대 무게
        K = readNumber();

        // 물건의 무게와 가치 입력
        for (int i = 0; i < N; i++) {
            int W = readNumber();
            int V = readNumber();
            cargos.add(new Cargo(W, V));
        }

        // 물건들의 가치의 최댓값 출력
        System.out.println(fillBackpack());
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return flag ? -cur : cur;
    }

    private static int fillBackpack() {
        int[][] bag = new int[N+1][K+1];

        // 모든 물건의 최대 무게까지 반복을 한다.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                // 해당 물건이 넣을 수 있는 무게일 경우, 해당 물건의 가치와 해당 물건의 무게를 넣을 공간이 있는 누적된 가치를 더한 값과
                // 그전까지 누적시킨 무게의 가치중 큰 값을 누적시킨다.
                if (cargos.get(i-1).wight <= j) {
                    bag[i][j] = Math.max(cargos.get(i-1).value + bag[i-1][j - cargos.get(i-1).wight], bag[i-1][j]);
                } else { // 아닐 경우, 그전까지 누적시킨 무게의 가치를 가져와 누적시킨다.
                    bag[i][j] = bag[i-1][j];
                }
            }
        }

        return bag[N][K];
    }
}

class Cargo {
    int wight;
    int value;

    public Cargo(int wight, int value) {
        this.wight = wight;
        this.value = value;
    }
}