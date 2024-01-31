import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] size;
    static ArrayList<Integer>[] nodes;
    static ArrayList<Integer>[] tree;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        int R = Integer.parseInt(stringTokenizer.nextToken());
        int Q = Integer.parseInt(stringTokenizer.nextToken());

        parent = new int[N + 1];
        size = new int[N + 1];
        nodes = new ArrayList[N + 1];
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            nodes[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        // 연결된 노드 표시
        for (int i = 0; i < N-1; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int nodeLeft = Integer.parseInt(stringTokenizer.nextToken());
            int nodeRight = Integer.parseInt(stringTokenizer.nextToken());

            nodes[nodeLeft].add(nodeRight);
            nodes[nodeRight].add(nodeLeft);
        }
        // 루트를 기준으로 트리 만들기
        makeTree(R, -1);

        // 노드의 개수 구하기
        countTree(R);

        for (int i = 0; i < Q; i++) {
            int node = Integer.parseInt(bufferedReader.readLine());
            stringBuilder.append(size[node]).append("\n");
        }

        System.out.println(stringBuilder);
    }

    public static void makeTree(int currentNode, int parentNode) {
        for (int node : nodes[currentNode]) {
            if (node != parentNode) {
                // 해당 노드의 부모 노드 표시
                parent[node] = currentNode;
                // 부모 노드에 해당 노드 담기
                tree[currentNode].add(node);
                makeTree(node, currentNode);
            }
        }
    }

    public static void countTree(int currentNode) {
        size[currentNode] = 1;

        for (int node : tree[currentNode]) {
            // 루트에 연결되어 있으면 카운트 증가
            countTree(node);
            size[currentNode] += size[node];
        }
    }
}