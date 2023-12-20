import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        List<Node>[] nodes = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int nodeA = Integer.parseInt(stringTokenizer.nextToken());
            int nodeB = Integer.parseInt(stringTokenizer.nextToken());
            int nodeLength = Integer.parseInt(stringTokenizer.nextToken());
            nodes[nodeA].add(new Node(nodeB, nodeLength));
            nodes[nodeB].add(new Node(nodeA, nodeLength));
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int nodeA = Integer.parseInt(stringTokenizer.nextToken());
            int nodeB = Integer.parseInt(stringTokenizer.nextToken());
            boolean[] visited = new boolean[N + 1];
            bufferedWriter.write(dfs(nodes, visited, nodeA, nodeB, 0) + "\n");
        }
        bufferedReader.close();

        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static int dfs(List<Node>[] nodes, boolean[] visited, int start, int end, int nodeLength) {
        int cnt = 0;
        if (start == end) {
            return nodeLength;
        }

        visited[start] = true;
        for (int i = 0; i < nodes[start].size(); i++) {
            if (!visited[nodes[start].get(i).connectNode]) {
                cnt += dfs(nodes, visited, nodes[start].get(i).connectNode, end, nodeLength + nodes[start].get(i).nodeLength);
            }
        }
        return cnt;
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