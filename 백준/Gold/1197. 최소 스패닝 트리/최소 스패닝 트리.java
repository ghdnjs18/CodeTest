import java.io.*;
import java.util.*;

public class Main {
    /**
     * 주어진 값 : 정점의 개수 V, 간선의 개수 E, E개의 간선 정보 (연결된 정점들과 가중치)
     * 조건 : 결과는 int 범위안에 있다,
     * 목표 : 정점들이 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리
     * 크루스칼 알고리즘은 스패닝 그래프만드는데 적합하다.
     * 간선 정보를 가중치를 기준으로 오름차순 정렬을 해서 가중치가 낮은 것 부터 간선을 선택해준다.
     * 간선을 선택하면서 정점간에 연결을 해주고 사이클이 발생하는 간선은 제외 해준다.
     * 연결된 간선이 정점의 수 - 1이 되면 스패닝 그래프를 만들어 줄 수 있다.
     */

    static List<Edge> edges = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 정점의 개수
        int V = Integer.parseInt(stringTokenizer.nextToken());
        // 간선의 개수
        int E = Integer.parseInt(stringTokenizer.nextToken());

        // 그래프 생성
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        // 간선 정보 입력
        for (int i = 0; i < E; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int vertexA = Integer.parseInt(stringTokenizer.nextToken());
            int vertexB = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());

            edges.add(new Edge(vertexA, vertexB, weight));
        }

        // 가중치 기준으로 오름차순 정렬
        edges.sort((o1, o2) -> o1.weight - o2.weight);

        int cnt = 0;
        int result = 0;
        // 최소의 간선만 선택해서 가중치를 누적시킨다.
        for (int i = 0; i < E; i++) {
            Edge edge = edges.get(i);
            int cur = edge.vertexA;
            int next = edge.vertexB;
            int weight = edge.weight;

            // 사이클이 아닌 경우에 가중치를 누적시킨다.
            if (findParent(cur) != findParent(next)) {
                unionParent(cur, next);
                result += weight;
                cnt++;
            }

            if (cnt == V - 1) break;
        }

        System.out.println(result);
    }

    private static int findParent(int vertex) {
        if (parent[vertex] == vertex) return vertex;
        return parent[vertex] = findParent(parent[vertex]);
    }

    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        parent[b] = a;
    }

    static class Edge {
        int vertexA;
        int vertexB;
        int weight;

        public Edge(int vertexA, int vertexB, int weight) {
            this.vertexA = vertexA;
            this.vertexB = vertexB;
            this.weight = weight;
        }
    }
}