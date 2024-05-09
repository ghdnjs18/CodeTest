import java.io.*;
import java.util.*;

public class Main {
    /**
     * 주어진 값 : 점의 개수 N, 진행된 차례의 수 M, M개의 연결 되는 점
     * 조건 : 세 점이 일직선이 되는 경우는 없다.
     * 목표 : 사이클이 처음으로 만들어진 차례 번호, 없으면 0 출력
     * 매 차레 마다 선을 연결하는 게 정해저 있기 때문에 입력시 바로바로 사이클 여부를 확인한다.
     * 연결된 점을 기준으로 동일한 점에 연결되어 있는지를 확인해 사이클을 여부를 구분한다.
     * 사이클이 아닐 경우, 한점을 기준으로 연결을 해준다.
     */


    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 점의 개수
        int N = Integer.parseInt(stringTokenizer.nextToken());
        // 진행된 차례
        int M = Integer.parseInt(stringTokenizer.nextToken());

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int result = 0;
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int vertexA = Integer.parseInt(stringTokenizer.nextToken());
            int vertexB = Integer.parseInt(stringTokenizer.nextToken());

            if (findParent(vertexA) == findParent(vertexB)) {
                result = i + 1;
                break;
            } else {
                unionParent(vertexA, vertexB);
            }
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
}