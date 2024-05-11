import java.io.*;

public class Main {
    /*
     * 주어진 값 : 문자열 2개
     * 목표 : 문자열 간에 연결되는 가장 긴 문자열
     * 두가지 값이 주어저 비교를 하면서 브루트포스 알고리즘으로 하기에는 경우의 수가 많고 시간 제한이 짧다? -> dp
     * 일단 먼저 두가지 값으로 표를 만들어서 규칙을 찾아 낸다.
     * 문자열이 같을 경우, 이전 값에서 +1이 되고 문자열이 틀릴 경우, 두 문자열의 이전값 중 큰 값이 들어오게 된다.
     * 이전 값에서 +1을 해야하기 때문에 dp배열은 문자열 배열보다 크기를 1크게 해서 첫값에서 -인덱스가 돼서 오류가 나지 않도록 해준다.
     * 마지막부터 비교하면서 dp의 값이 행과 열의 값에서 모두 다를 경우, 문자가 같을 경우이므로 해당 문자를 추가해준다.
     * 반복이 끝나고 뒤에서 부터 문자를 뒤에서 부터 추적해 왔기떄문에 문자열을 반전시켜 출력해준다.
     * */

    public static void main(String[] args) throws IOException {
        // 문자열 입력
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstStr = bufferedReader.readLine().split("");
        String[] secondStr = bufferedReader.readLine().split("");

        int[][] dp = new int[firstStr.length + 1][secondStr.length + 1];
        for (int i = 1; i <= firstStr.length; i++) {
            for (int j = 1; j <= secondStr.length; j++) {
                if (firstStr[i - 1].equals(secondStr[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        int x = firstStr.length;
        int y = secondStr.length;
        while (x > 0 && y > 0) {
            if (dp[x][y] == dp[x - 1][y]) {
                x--;
            } else if (dp[x][y] == dp[x][y - 1]) {
                y--;
            } else {
                stringBuilder.append(firstStr[x - 1]);
                x--;
                y--;
            }
        }

        System.out.println(dp[firstStr.length][secondStr.length]);
        System.out.println(stringBuilder.reverse());
    }
}
