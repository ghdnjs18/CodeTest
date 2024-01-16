import java.io.*;
import java.util.*;

public class Main {
    /*
    * 총 범위 1~F, 현재 위치 S, 도착 위치 G, 앞으로 이동하는 거리 U, 뒤로 이동하는 거리 D
    * G층에 도착할 수 있다면 이동 횟수 출력, 없다면 "use the stairs"출력
    * 한번 간 층은 한번 더 이동안하도록 표시해서 사용한다.
    * U와 D로 이동 가능 여부를 확인하면서 가능한 방법을 찾아 최소값을 도출한다.
    * 시작층과 도착층이 같으면 이동필요없이 0을 출력해야한다.
    * */

    static boolean[] visited;
    static int[] move;
    static int F, G;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력값 입력
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        F = Integer.parseInt(stringTokenizer.nextToken());
        int S = Integer.parseInt(stringTokenizer.nextToken());
        G = Integer.parseInt(stringTokenizer.nextToken());
        int U = Integer.parseInt(stringTokenizer.nextToken());
        int D = Integer.parseInt(stringTokenizer.nextToken());
        move = new int[]{U, -D};
        
        // 사용 여부 확인 마지막 층까지이기 때문에 +1해준다.
        visited = new boolean[F+1];
        visited[S] = true;
        int cnt = move(S, 0);

        // 시작층이 도착층이면 버튼을 누를필요없다.
        if (S == G || cnt != 0) {
            System.out.println(cnt);
        } else {
            System.out.println("use the stairs");
        }
    }
    
    public static int move(int S, int start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{S, start});

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int current = temp[0];
            int cnt = temp[1];

            if (current == G) return cnt;

            for (int i = 0; i < 2; i++) {
                int next = current + move[i];
                if (next > 0 && next <= F && !visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, cnt + 1});
                }
            }
        }

        return 0;
    }
}