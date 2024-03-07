import java.io.*;
import java.util.*;

public class Main {
    /*
    * 두 학생끼리 키를 비교한 결과의 일부가 주어진다.-> 방향성이 있는 그래프를 파악할 수 있다.
    * 자신의 키가 몇 번째인지 알 수 있는 학생은 모든 정점과 연결된 정점이다.
    * 방향 그래프를 이용해 주어진 정점에서 모두 연결된 정점을 찾는다.
    * */

    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static List<List<Integer>> reversGraph = new ArrayList<>();
    static boolean[] visited;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        // 학생들의 수
        N = readNumber();
        // 두 학생 키를 비교한 횟수
        M = readNumber();

        // 학생 키를 비교한 정보를 저장하기 위한 리스트 생성
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i <= N; i++) {
            reversGraph.add(new ArrayList<>());
        }

        // 학생 키를 비교한 정보 입력
        for (int i = 0; i < M; i++) {
            int nodeA = readNumber();
            int nodeB = readNumber();

            graph.get(nodeA).add(nodeB);
            reversGraph.get(nodeB).add(nodeA);
        }

        // 비교한 결과 확인
        cnt = new int[N+1];
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N+1];
            frontSearch(i);
            backSearch(i);
        }

        // 정방향과 역방향으로 사용된 정점의 수가 전체 노드 수 + 1일 경우 헤아려준다. 
        // 시작 노드가 정방향, 역방향에서 중복으로 사용되기 때문에 + 1 해준다. 
        int answer = 0;
        for (int i : cnt) {
            if (i == N + 1) {
                answer++;
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

    private static void frontSearch(int cur) {
        cnt[cur]++;
        visited[cur] = true;
        for (int next : graph.get(cur)) {
            if (!visited[next]) {
                frontSearch(next);
            }
        }
    }

    private static void backSearch(int cur) {
        cnt[cur]++;
        visited[cur] = true;
        for (int next : reversGraph.get(cur)) {
            if (!visited[next]) {
                backSearch(next);
            }
        }
    }
}