import java.io.*;
import java.util.*;

public class Main {
    /*
    * 현재 수 N, 최대 버튼 횟수 T, 목표 수 G
    * 숫자의 이동 방식 2가지
    * 1. N이 1증가.
    * 2. N * 2 후 0이 아닌 가장 높은 자리수 -1 ex) 123 -> 246 -> 146, 5 -> 10 -> 00, 3 -> 6 -> 5
    * 실패 조건 - 99999를 넘어가는 순간 실패 -> 실패시 ANG 출력
    * 각각 숫자 이동 방식이 99999를 초과하는지 확인을 하고 수를 반복해준다.
    * */

    static int currentNum, maxCount, targetNum;
    static boolean[] visited = new boolean[100000];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        currentNum = Integer.parseInt(stringTokenizer.nextToken());
        maxCount = Integer.parseInt(stringTokenizer.nextToken());
        targetNum = Integer.parseInt(stringTokenizer.nextToken());

        int cnt = bfs();

        if (cnt == -1) {
            System.out.println("ANG");
        } else {
            System.out.println(cnt);
        }
    }

    private static int bfs() {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{currentNum, 0});
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int num = temp[0];
            int cnt = temp[1];

            if (num == targetNum) {
                return cnt;
            }

            // 99999초과 하지않고 최대 횟수를 넘지않고 사용하지 않는 경우 A버튼 누른다.
            if (num + 1 < 100000 && cnt < maxCount && !visited[num + 1]) {
                visited[num] = true;
                queue.offer(new int[]{num + 1, cnt + 1});
            }

            // 먼저 곱하기 2를 했을 때 최대 숫자를 초과하는지 확인을 한다.
            if (num > 0 && num * 2 < 100000) {
                // 곱한 수에서 자리에 맞게 뺄 자리수를 구한다.
                int nextNum = num * 2;
                int minus = 1;
                while (nextNum > 9) {
                    nextNum /= 10;
                    minus *= 10;
                }
                if (cnt < maxCount && !visited[num * 2 - minus]) {
                    visited[num * 2 - minus] = true;
                    queue.offer(new int[]{num * 2 - minus, cnt + 1});
                }
            }
        }

        return -1;
    }
}