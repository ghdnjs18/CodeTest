import java.io.*;
import java.util.*;

public class Main {
    /*
    * 공간적으로 생각을 하고 분석으 해보았다.
    * 나올 수 있는 면들은 모서리(3면), 테두리(2면), 한쪽(1면)이다.
    * 최소가 되기 위해서는 한쪽면만 보이는 곳은 전부 가장 작은 수,
    * 테두리는 가장 작은 2개의 수, 모서리는 가장 작은 3개의 수
    * 제일 작은 수 * (N - 2) ^ 2 (테두리를 제외한 윗면의 중앙)
    * 제일 작은 수 * (N - 1) * (N - 2) * 2 (테두리를 제외한 옆면의 중앙)
    * 제일 작은 수 * ((N - 1) * 4 + (N - 2) * 4) (윗면과 옆면에서 모서리 수 제외한 테두리 수)
    * 그다음 작은 수 * ((N - 1) * 4 + (N - 2) * 4) (윗면과 옆면에서 모서리 수 제외한 테두리 수)
    * 제일 작은 수 * 4 (모서리 수)
    * 그다음 작은 수 * 4 (모서리 수)
    * 그그 다음 작은 수 * 4 (모서리 수)
    * 주사위가 하나일 경우는 가장 큰 수만 빼준다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        long N = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] dice = new int[6];
        int maxDice = 0;
        long sum = 0;
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(stringTokenizer.nextToken());
            sum += dice[i];
            maxDice = Math.max(maxDice, dice[i]);
        }

        if (N > 1) {
            sum = 0;

            // 전개도에 맞게 작은 수 3개 선정
            int[] numbers = new int[3];
            numbers[0] = Math.min(dice[0], dice[5]);
            numbers[1] = Math.min(dice[1], dice[4]);
            numbers[2] = Math.min(dice[2], dice[3]);
            Arrays.sort(numbers);

            // 위면 중앙
            sum += numbers[0] * (N - 2) * (N - 2);
            // 옆면 중앙
            sum += numbers[0] * (N - 1) * (N - 2) * 4;
            // 테두리
            sum += numbers[0] * ((N - 1) * 4 + (N - 2) * 4);
            sum += numbers[1] * ((N - 1) * 4 + (N - 2) * 4);
            // 모서리
            sum += numbers[0] * 4;
            sum += numbers[1] * 4;
            sum += numbers[2] * 4;

            System.out.println(sum);
        } else {
            // 주사위가 하나일 경우는 가장 큰 수만 빼준다.
            System.out.println(sum - maxDice);
        }
    }
}