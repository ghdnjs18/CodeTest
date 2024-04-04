import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 노드의 개수 N, 연결된 간선의 노드와 간선의 길이(1 ~ 100), 루트 노드는 항상 1
    * 목표 : 트리의 지름 - 모든 경로중 가장 긴 간선의 합
    * 지름이 가장 길려면 끝에서 끝으로 가야하기 때문에 리프 노드끼리에서만 나올 수 있다.
    * 모든 노드를 탐색하지 않고 리프 노드만 깊이 탐색을 이욯해 가장 긴 지름을 구할 수 있다.
    * 메모리 119092kb 시간 1592
    * ----------------
    * 다른 사람의 풀이를 보고 깨달음
    * 루트를 기준으로 깊이 탐색을 했을 때, 가장 긴 경로를 가진 리프 노드를 알 수 있다.
    * -> 루트에서 모든 리프 노드를 탐색이 가능한데 그중에서 가장 길면 트리에서 가장 긴 경로를 가질려면 해당 리프 노드를 지나갈 것이다.
    * -> 그러면 리프노드를 확인할 필요도 없고 모든 리프 노드가 아닌 해당 리프 노드만 탐색하면 가장 긴 경로를 알 수 있다.
    * */

    static List<List<Node>> tree;
    static boolean[] visited;
    static int leafNode, max;

    public static void main(String[] args) throws IOException {

        // 노드의 개수
        int N = readNumber();

        // 트리 리스트 생성
        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        // 트리 간선 입력
        boolean[] isleaf = new boolean[N + 1];
        for (int i = 0; i < N-1; i++) {
            int nodeA = readNumber();
            int nodeB = readNumber();
            int length = readNumber();

//            isleaf[nodeA] = true;

            tree.get(nodeA).add(new Node(nodeB, length));
            tree.get(nodeB).add(new Node(nodeA, length));
        }

        // 리프 노드 표시
//        Queue<Integer> leafNode = new LinkedList<>();
//        for (int i = 2; i <= N; i++) {
//            if (!isleaf[i]) {
//                leafNode.offer(i);
//            }
//        }

        // 리프 노드 깊이 탐색
//        int max = 0;
//        while (!leafNode.isEmpty()) {
//            visited = new boolean[N + 1];
//            int num = leafNode.poll();
//            System.out.println(num + " : " + sumNode(num, 0));
//            max = Math.max(max, sumNode(leafNode.poll(), 0));
//        }

        visited = new boolean[N + 1];
        sumNode(1, 0);
//        System.out.println(leafNode);

        visited = new boolean[N + 1];
        int max = sumNode(leafNode, 0);
//        System.out.println(leafNode);

        System.out.println(max);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        return flag ? -cur : cur;
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
            if (!visited[next.connectNode]) {
                nextVal = Math.max(nextVal, sumNode(next.connectNode, val + next.nodeLength));
            }
        }
        return nextVal;
    }
}

class Node {
    int connectNode;
    int nodeLength;

    public Node(int connectNode, int nodeLength) {
        this.connectNode = connectNode;
        this.nodeLength = nodeLength;
    }
}