import java.io.*;
import java.util.*;

public class Main {

    /**
     * 주어진 값 : 집의 개수 N, 집간 연결 정보의 개수 M (연결된 두 집, 길을 유지하는 유지비)
     * 조건 : 두 마을 사이는 길이 있을 필요가 없다.
     * 목표 : 유지비용을 최소로 집간의 길을 연결해 마을을 나눈다.
     * 최소 신장 트리를 이용해서 길의 유지비가 작은 순으로 정렬을 한다.
     * 유지비가 작은 길부터 연결을 하면서 유지비를 누적시켜준다.
     * 길연결시 순환하게 되는 길은 제외시켜준다.
     * 연결된 길중에 가장 큰 유지비인 길을 끊어 마을을 나누기 위해 연결된 길이 집의 개수 - 2이 되면 반복을 종료한다.
     */

    static int N, M;
    static int[] house;
    static Load[] loads;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 집의 개수
        N = Integer.parseInt(stringTokenizer.nextToken());
        // 길의 개수
        M = Integer.parseInt(stringTokenizer.nextToken());

        // 길의 정보 입력
        loads = new Load[M];
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int firstHouse = Integer.parseInt(stringTokenizer.nextToken());
            int secondHouse = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());

            loads[i] = new Load(firstHouse, secondHouse, cost);
        }

        // 유지비기준으로 오름차순 정렬
        Arrays.sort(loads, (o1, o2) -> o1.cost - o2.cost);

        // 집의 연결 정보
        house = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            house[i] = i;
        }

        System.out.println(getCost());
    }

    private static int getCost() {
        int cnt = 0;
        int result = 0;
        for (int i = 0; i < loads.length; i++) {
            Load load = loads[i];
            int firstHouse = load.firstHouse;
            int secondHouse = load.secondHouse;

            if (cnt == N - 2) break;
            
            if (findHouse(firstHouse) != findHouse(secondHouse)) {
                unionHouse(firstHouse, secondHouse);
                cnt++;
                result += load.cost;
            }
        }
        return result;
    }

    private static int findHouse(int idx) {
        if (house[idx] == idx) return idx;
        return house[idx] = findHouse(house[idx]);
    }

    private static void unionHouse(int firstHouse, int secondHouse) {
        firstHouse = findHouse(firstHouse);
        secondHouse = findHouse(secondHouse);

        house[firstHouse] = secondHouse;
    }

    static class Load {
        int firstHouse;
        int secondHouse;
        int cost;

        public Load(int firstHouse, int secondHouse, int cost) {
            this.firstHouse = firstHouse;
            this.secondHouse = secondHouse;
            this.cost = cost;
        }
    }
}