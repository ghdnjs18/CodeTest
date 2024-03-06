import java.io.*;
import java.util.*;

public class Main {

    /*
    * A는 B와 친구 B는 C와 친구 C는 D와 친구 D는 E와 친구
    * 연결 그래프의 길이가 4이상인 그래프는 찾는 문제
    * */
    static int N, M;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] visited;
    static boolean check;

    public static void main(String[] args) throws IOException {
        N = readNumber();
        M = readNumber();

        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int nodeA = readNumber();
            int nodeB = readNumber();
            list.get(nodeA).add(nodeB);
            list.get(nodeB).add(nodeA);
        }

        check = false;
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            findFriend(i, 0);
            visited[i] = false;

            if (check) break;
        }

        if (check) System.out.println(1);
        else System.out.println(0);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static void findFriend(int current, int cnt) {
        if (cnt > 3) {
            check = true;
            return;
        }

        for (int next : list.get(current)) {
            if (!visited[next]) {
                visited[next] = true;
                findFriend(next, cnt + 1);
                visited[next] = false;
            }
        }
    }
}