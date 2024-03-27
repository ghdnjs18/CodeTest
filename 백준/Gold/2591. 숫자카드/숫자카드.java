import java.io.*;

public class Main {
    /*
    * 주어진 값 : 40자 이하의 숫자
    * 목표 : 주어진 숫자의 조합의 수 (제한 1~34)
    * 카드를 하나씩 뽑으면서 카드의 조합이 뽑을 수 있는 카드인지 확인하여 경우의 수를 누적시킨다.
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String num = bufferedReader.readLine();

        int[] dp = new int[num.length() + 1];
        // 처음 뽑은 2장의 카드가 합쳐질 경우 더해주기 위해
        dp[0] = 1;
        // 처음 뽑는 카드는 무조건 한번
        dp[1] = 1;
        for (int i = 1; i < num.length(); i++) {
            // 0이 아니면 카드를 뽑을 수 있다.
            if (!num.substring(i, i+1).equals("0")) {
                dp[i + 1] += dp[i];
            }
            // 뽑은 카드가 이전 카드와 합쳤을 때 34 보다 작으면 경우의 수를 누적 시킨다.
            if (!num.substring(i-1, i).equals("0")) {
                int merge = Integer.parseInt(num.substring(i-1, i+1));
                if (1 <= merge && merge <= 34) {
                    dp[i + 1] += dp[i - 1];
                }
            }
        }

        System.out.println(dp[num.length()]);
    }
}