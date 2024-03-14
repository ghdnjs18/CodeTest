import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어지는 값 : 노수의 수 N, N-1개의 간선, 노드의 값, 루트는 무조건 0번 노드
    * 목표 : 모든 경로를 탐색하며 최댓값을 찾아 출력
    * 트리의 경로를 깊이 탐색을 하며 노드의 값을 더해준다. 루트를 기준으로 전체 탐색을 하면 반복 방문은 없다.
    * 리프 노드까지 값을 더했을 때 양수인 경로들을 다 더하면 최댓값이 될 수 있다.
    * 26% 실패 출력값이 int의 범위를 벗어날수있다? -> long으로 교체
    * 32% 시간 초과 -> 중복 방문이 생기는가? -> visited배열을 이용해 값을 넣어주고 반복시 해당 값을 리턴한다.
    * */

    static int N;
    static List<List<Integer>> tree = new ArrayList<>();
    static int[] nodeValues;
    static long[] visited;

    public static void main(String[] args) throws IOException {
        // 노드의 수
        N = readNumber();

        // 트리 리스트 생성
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        // 트리 간선 입력
        for (int i = 0; i < N-1; i++) {
            int nodeA = readNumber();
            int nodeB = readNumber();

            tree.get(nodeA).add(nodeB);
        }

        // 노드의 값 입력
        nodeValues = new int[N];
        for (int i = 0; i < N; i++) {
            nodeValues[i] = readNumber();
        }

        // 최댓값 출력
        visited = new long[N];
        System.out.println(sumNode(0));
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

    private static long sumNode(int cur) {
        // 리프 노드일 경우 값 리턴
        if (tree.get(cur).isEmpty()) {
            return nodeValues[cur];
        }
        if (visited[cur] != 0) {
            return visited[cur];
        }

        long val = nodeValues[cur];
        for (int next : tree.get(cur)) {
            // 리프 노드까지 갔을 때, 양수면 값을 더해준다.
            if (sumNode(next) > 0) {
                val += sumNode(next);
                visited[cur] = val;
            }
        }
        return val;
    }
}