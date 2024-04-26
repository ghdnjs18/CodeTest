import java.io.*;
import java.util.*;

public class Main {

    /**
     * 주어진 값 : 트리의 정점의 개수 V, 연결된 간선의 노드와 거리
     * 조건 : 정점 별로 연결된 간선 정보가 주어진다. (1번과 3번의 간선은 1번과 3번에서 입력이 따로 된다.)
     * 목표 : 트리에서 두점 사이의 가장 긴 거리 출력
     * 리스트안에 리스트를 넣어 연결된 정점과 거리를 입력한다.
     * 임의의 정점에서 깊이 탐색을 통해 가장 긴 거리에 있는 정점을 찾아 해당 정점을 리프 노드로 만든다.
     * 리프 노드를 기준으로 동일하게 가장 긴 거리를 출력한다.
     */

    static List<List<Node>> tree = new ArrayList<>();;
    static boolean[] visited;
    static int leafNode, max;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i <= V; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int nodeA = Integer.parseInt(stringTokenizer.nextToken());
            while (true) {
                int nodeB = Integer.parseInt(stringTokenizer.nextToken());
                if (nodeB == -1) {
                    break;
                }
                int length = Integer.parseInt(stringTokenizer.nextToken());

                tree.get(nodeA).add(new Node(nodeB, length));
            }
        }

        visited = new boolean[V + 1];
        sumNode(1, 0);

        visited = new boolean[V + 1];
        int max = sumNode(leafNode, 0);

        System.out.println(max);
    }

    private static int sumNode(int cur, int val) {

        visited[cur] = true;

        // 가장 긴 경로와 노드를 확인 가능해 리턴값을 int로 안 잡아도된다.
        if (val > max) {
            max = val;
            leafNode = cur;
        }

        int nextVal = val;
        for (Node next : tree.get(cur)) {
            if (!visited[next.node]) {
                nextVal = Math.max(nextVal, sumNode(next.node, val + next.length));
            }
        }
        return nextVal;
    }

    static class Node {
        int node;
        int length;

        public Node(int node, int length) {
            this.node = node;
            this.length = length;
        }
    }
}