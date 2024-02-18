import java.io.*;
import java.util.*;

public class Main {

    /*
    * 연산을 3가지로 나눠 너비 탐색안에서 진행을 해준다.
    * 1. 이모티콘 복사해 클립보드 저장 -> 이모티콘은 변화없음, 클립보드 이모티콘로 변경
    * 2. 클립보드 붙여넣기 -> 이모티콘 클립보드 수만큼 증가, 클립보드 변화없음
    * 3. 이모티콘 하나 삭제 -> 이모티콘 하나 감소, 클립보드 변화없음
    * 사용 여부를 확인하기 위해서 이모티콘 수와 클립보드 수로 이차배열을 만들어 구분한다.
    * 목표수가 되면 횟수 출력
    * */
    static int target;
    static boolean[][] visited = new boolean[1001][1001];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(bufferedReader.readLine());

        System.out.println(makeEmote());
    }

    private static int makeEmote() {
        int[] cases = {1, 2, 3};

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0, 0});
        visited[1][0] = true;
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int num = temp[0];
            int cnt = temp[1];
            int crip = temp[2];

            if (target == num) {
                return cnt;
            }

            int nextNum = 0;
            int nextCrip = 0;
            for (int i = 0; i < cases.length; i++) {
                if (cases[i] == 1) {
                    nextNum = num;
                    nextCrip = num;
                } else if (cases[i] == 2) {
                    nextNum = num + crip;
                    nextCrip = crip;
                } else {
                    nextNum = num - 1;
                    nextCrip = crip;
                }

                if (nextNum <= target && nextNum > 0 && nextCrip <= 1000 && !visited[nextNum][nextCrip]) {
                    visited[nextNum][nextCrip] = true;

                    queue.offer(new int[]{nextNum, cnt + 1, nextCrip});
                }
            }
        }

        return 0;
    }
}