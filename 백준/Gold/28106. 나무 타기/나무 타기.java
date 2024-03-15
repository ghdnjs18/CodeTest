import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어지는 값 : 정점의 수 N, N개의 간선, 정점의 값
    * 목표 : 깊이 탐색을 할 수 있는 경우의 수
    * 그래프가 트리 구조를 보장한다. 루트 노드에서 시작해 리프 노드로 탐색
    * 루트 노드에서 정점의 값으로 갈 수 있는 깊이의 정점에서 리프 노드로 갈 수 있는 횟수를 헤아린다.
    * 루트 노드에서 움직인 정점을 루트 노드로 서브 트리를 만들어 위 방법을 반복한다.
    * 경우의 수 출력시 998244353으로 나눈 나머지를 출력한다.
    * */

    static int N;
    static List<List<Integer>> tree = new ArrayList<>();
    static int[] nodeValue;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        // 정점의 수
        N = readNumber();

        // 트리 리스트 생성
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        // 트리 간선 입력
        int root = 0;
        for (int i = 1; i <= N; i++) {
            int node = readNumber();
            tree.get(node).add(i);
            if (node == 0) root = i;
        }

        // 정점의 값 입력
        nodeValue = new int[N+1];
        dp = new int[N+1];
        for (int i = 1; i <= N; i++) {
            nodeValue[i] = readNumber();
            dp[i] = -1;
        }

        System.out.println(findLeafTree(root));

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

    private static int findLeafTree(int cur) {
        if (tree.get(cur).isEmpty()) {
            return 1;
        }

        if (nodeValue[cur] == 0) {
            return 0;
        }
        
        if (dp[cur] != -1) {
            return dp[cur];
        }

        dp[cur] = 0;
        List<Integer> subTree = new ArrayList<>();
        subTree = findSubTree(cur, cur, 0, subTree);
        for (int next : subTree) {
            dp[cur] += findLeafTree(next);
            dp[cur] %= 998244353;
        }

        return dp[cur] % 998244353;
    }

    private static List<Integer> findSubTree(int cur, int root, int length, List<Integer> subTree) {
        if (1 <= length && length <= nodeValue[root]) {
            subTree.add(cur);
        }

        for (int next : tree.get(cur)) {
            findSubTree(next, root, length + 1, subTree);
        }

        return subTree;
    }
}