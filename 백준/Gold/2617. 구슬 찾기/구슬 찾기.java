import java.io.*;
import java.util.*;

public class Main {
    /*
     * 한 쌍의 구슬을 골라 어느쪽이 무거운지 비교를 한다. -> 그래프 탐색
     * 정방향과 역방향으로 가벼운 순과 무거운 순으로 중간 구슬의 무게와 비교한다.
     * 전체의 중간인 구슬인 (N+1) / 2 보다 크면 중간이 될 수 없다.
     * */

    static int N, M;
    static List<List<Integer>> heavyList = new ArrayList<>();
    static List<List<Integer>> lightList = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // 구슬의 개수
        N = readNumber();
        // 저울에 올려 본 쌍의 개수
        M = readNumber();

        // 비교를 위한 리스트 생성
        for (int i = 0; i <= N; i++) {
            heavyList.add(new ArrayList<>());
            lightList.add(new ArrayList<>());
        }

        // 구슬의 비교 정보를 입력한다.
        for (int i = 0; i < M; i++) {
            int nodeA = readNumber();
            int nodeB = readNumber();

            heavyList.get(nodeA).add(nodeB);
            lightList.get(nodeB).add(nodeA);
        }

        int mid = (N+1) / 2;
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N+1];
            // 자신 보다 가볍거나 무거운 구슬의 수가 반이상이 되는경우 중간인 구슬이 될 수 없다.
            if (compareWeight(lightList, i) >= mid || compareWeight(heavyList, i) >= mid) {
                answer += 1;
            }
        }
        System.out.println(answer);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static int compareWeight(List<List<Integer>> list, int cur) {
        visited[cur] = true;

        int cnt = 0;
        for (int next : list.get(cur)) {
            if (!visited[next]) {
                cnt += 1;
                cnt += compareWeight(list, next);
            }
        }
        return cnt;
    }
}