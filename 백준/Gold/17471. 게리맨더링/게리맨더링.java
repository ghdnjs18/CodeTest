import java.io.*;
import java.util.*;

public class Main {
    /*
    * N의 수가 10이기 때문에 웬만한 깊이 탐색으로 가능할 것 같다.
    * 깊이 탐색으로 지역들을 연결할 수 있는 조합으로 반복을 한다.
    * 각 선거구는 적어도 하나의 구역을 포함해야 한다. -> 모든 지역을 사용했을 때 비교할 리스트 두 곳에 값이 있어야한다.
    * 나눠진 구역들이 모두 연결되어 있는지 연결 정보로 확인을 한다.
    * 두 구역으로 나눠진 상태에서 모든 구역이 연결되어 있다면, 주어진 인구 정보로 구역 간 인구 차이를 구한다.
    * 구할 수 있는 모든 구분을 하면서 최소의 인구 차이를 구한다.
    * */

    static int N, populationDiff;
    static int[] population;
    static List<List<Integer>> connectCity;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 구역의 개수
        N = Integer.parseInt(bufferedReader.readLine());

        // 구역의 연결을 할 리스트를 구역 번호 만큼 초기화
        connectCity = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            connectCity.add(new ArrayList<>());
        }

        // 구역의 인구수 입력
        population = new int[N+1];
        visited = new boolean[N+1];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        // 구역의 연결 입력
        for(int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int adjacentCityCnt = Integer.parseInt(stringTokenizer.nextToken());
            for (int j = 0; j < adjacentCityCnt; j++) {
                connectCity.get(i).add(Integer.parseInt(stringTokenizer.nextToken()));
            }
        }

        populationDiff = Integer.MAX_VALUE;
        divideZone(1);

        if (populationDiff == Integer.MAX_VALUE) populationDiff = -1;
        System.out.println(populationDiff);
    }

    private static void divideZone(int index) {
        if (index == N) {
            List<Integer> zoneA = new ArrayList<>();
            List<Integer> zoneB = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (visited[i]) zoneA.add(i);
                else zoneB.add(i);
            }
            // 각 선거구는 적어도 하나의 구역을 포함해야 한다.
            if (zoneA.isEmpty() || zoneB.isEmpty()) return;

            // 구역들이 모두 연결됐다면 구역 간 인구 수 차이를 구한다.
            if (isConnect(zoneA) && isConnect(zoneB)) getPopulationDiff();
            return;
        }

        visited[index] = true;
        divideZone(index + 1);
        visited[index] = false;
        divideZone(index + 1);
    }

    private static boolean isConnect(List<Integer> list) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        visited[list.get(0)] = true;
        queue.offer(list.get(0));

        // 방문한 지역 개수
        int cnt = 1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < connectCity.get(cur).size(); i++) {
                int next = connectCity.get(cur).get(i);

                if (list.contains(next) && !visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                    cnt += 1;
                }
            }
        }

        // 방문한 지역 개수와 연결된 구역의 수가 같은면 모두 연결됨
        return cnt == list.size();
    }

    private static void getPopulationDiff() {
        int populationA = 0;
        int populationB = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) populationA += population[i];
            else populationB += population[i];
        }

        int diff = Math.abs(populationA - populationB);
        populationDiff = Math.min(populationDiff, diff);
    }
}