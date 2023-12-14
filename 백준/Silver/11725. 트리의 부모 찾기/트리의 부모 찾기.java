import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int[] parents = new int[N + 1];
        ArrayList<Integer>[] nodes = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
             nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int nodeLeft = Integer.parseInt(stringTokenizer.nextToken());
            int nodeRight = Integer.parseInt(stringTokenizer.nextToken());

            nodes[nodeLeft].add(nodeRight);
            nodes[nodeRight].add(nodeLeft);
        }

        dfs(parents, nodes, 1, 1);

        for (int i = 2; i <= N; i++) {
            System.out.println(parents[i]);
        }
    }
    
    public static void dfs (int[] parents, ArrayList<Integer>[] nodes, int parentNode, int parent) {
        for (int node : nodes[parentNode]) {
            if (node == parent) continue;
            parents[node] = parentNode;
            dfs(parents, nodes, node, parentNode);
        }
    }
}