import java.io.*;
import java.util.*;

public class Main {

    /*
    * 그래프 탐색으로 인접한 노드에서 사이클이 발생하는지 확인하는 문제
    * 동그라미의 개수 -> 노드의 개수, 직선들의 개수 -> 노드간 연결 정보
    * 탐색은 깊이 탐색과 너비 탐색으로 가능하다.
    * */
    static List<List<Integer>> connectCircle;
    static int[] circle;
    static boolean check;

    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        // 테스트 케이스의 개수
        int T = readNumber();

        // 테스트 케이스 입력 받기
        for (int i = 0; i < T; i++) {
            // 동그라미의 개수
            int N = readNumber();
            // 직선들의 개수
            int M = readNumber();

            // 동그라미 직선 연결
            connectCircle = new ArrayList<>();
            for (int j = 0; j <= N; j++) {
                connectCircle.add(new ArrayList<>());
            }

            circle = new int[N + 1];
            check = false;
            for (int j = 0; j < M; j++) {
                int lineA = readNumber();
                int lineB = readNumber();
                connectCircle.get(lineA).add(lineB);
                connectCircle.get(lineB).add(lineA);
            }

            for (int j = 1; j <= N; j++) {
//                if (circle[j] == 0) {
//                    circle[j] = 1;
//                    isCycleDFS(j);
//                }
                if (circle[j] == 0) isCycleBFS(j);
                if (check) break;
            }

            if (check) stringBuilder.append("impossible").append("\n");
            else stringBuilder.append("possible").append("\n");
        }

        System.out.println(stringBuilder);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static void isCycleDFS(int current) {
        for (int next : connectCircle.get(current)) {
            if (circle[next] == 0) {
                circle[next] = 3 - circle[current];
                isCycleDFS(next);
            }
            if (circle[current] == circle[next]) {
                check = true;
            }
        }
    }

    private static void isCycleBFS(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        circle[start] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : connectCircle.get(cur)) {
                if (circle[next] == 0) {
                    queue.add(next);
                    circle[next] = circle[cur] * -1;
                } else if (circle[next] + circle[cur] != 0) {
                    check = true;
                    return;
                }
            }
        }
    }
}