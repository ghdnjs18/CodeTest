import java.io.*;
import java.util.*;

public class Main {
    /*
    * 상사가 직속 부하를 칭찬하면 연쇄적으로 내리 칭찬을 한다. -> 방향 그래프로 깊이 탐색을한다.
    * 연쇄적으로 내리 칭찬을 하는 경우는 반복될 경우 재활용이 가능하다. -> DP를 이용해서 처리할 수 있다.
    * 추가 수정 -> 사용 여부와 정답 배열을 추가로 사용해서 시간을 단축 할 수 있다.
    * */

    static int N, M;
    static List<List<Integer>> relation;
    static int[] praise;
    static int[] answer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 회사의 직원 수
        N = Integer.parseInt(stringTokenizer.nextToken());
        // 최초의 칭찬의 횟수
        M = Integer.parseInt(stringTokenizer.nextToken());

        // 직속 상사의 번호 입력
        relation = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            relation.add(new ArrayList<>());
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= N; i++) {
            int people = Integer.parseInt(stringTokenizer.nextToken());
            if (people == -1) people = 0;
            relation.get(people).add(i);
        }

        // 칭찬 정보 입력
        praise = new int[N+1];
        answer = new int[N+1];
        visited = new boolean[N+1];
        for (int j = 0; j < M; j++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int people = Integer.parseInt(stringTokenizer.nextToken());
            int raiseSize = Integer.parseInt(stringTokenizer.nextToken());

            praise[people] += raiseSize;
        }

        // 내리 칭찬
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) startPraise(1);
        }

        // 칭찬 횟수 출력
        for (int i = 1; i <= N; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    private static void startPraise(int cur) {
        visited[cur] = true;
        answer[cur] += praise[cur];
        for (int next : relation.get(cur)) {
            if (!visited[next]) {
                answer[next] += answer[cur];
                startPraise(next);
            }
        }
    }
}