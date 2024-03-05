import java.io.*;
import java.util.*;

public class Main {

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

            
            connectCircle = new ArrayList<>();
            for (int j = 0; j <= N; j++) {
                connectCircle.add(new ArrayList<>());
            }
            
            // 동그라미 직선 연결
            for (int j = 0; j < M; j++) {
                int lineA = readNumber();
                int lineB = readNumber();
                connectCircle.get(lineA).add(lineB);
                connectCircle.get(lineB).add(lineA);
            }

            circle = new int[N + 1];
            check = false;
            for (int j = 1; j <= N; j++) {
                if (circle[j] == 0) bfs(j);
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

    private static void bfs(int start){
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