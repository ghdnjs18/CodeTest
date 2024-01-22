import java.io.*;
import java.util.*;

public class Main {
    /*
    * 와이어의 비용은 와이어 길이의 제곱이기 때문에 산맥 정상같의 거리 제곱 + 산맥 높이의 차이 제곱 이다.
    * 비용을 최소화 하기 위해서는 모든 산을 거처 방법이 최소로 할 수 있다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int[] mountains = new int[N];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            mountains[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int sum = 0;
        for (int i = 0; i < N-1; i++) {
            int plus = mountains[i] + mountains[i+1];
            int minus = mountains[i] - mountains[i+1];
            sum += plus*plus + minus*minus;
        }
        System.out.println(sum);
    }
}