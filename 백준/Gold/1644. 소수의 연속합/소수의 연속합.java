import java.io.*;
import java.util.*;

public class Main {
    /**
     * 주어진 값 : 자연수 N
     * 조건 : 연속된 소수의 합으로 자연수 N을 만든다. 동일한 소수는 한번만 사용이 가능하다.
     * 목표 : 자연수 N을 만들 수 있는 소수의 합의 개수
     * N보다 같거나 작은 소수들을 구한다.
     * 해당 소수들을 작은 값부터 더해서 N이 되는지 확인한다. N보다 커질 경우 작은 값부터 빼주면서 반복한다.
     * N이 성립될 경우, 횟수를 헤아린다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        List<Integer> prime = new ArrayList<>();
        boolean[] isprime = new boolean[N + 1];
        for (int i = 2; i * i <= N; i++) {
            if (!isprime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isprime[j] = true;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (!isprime[i]) prime.add(i);
        }

        int left = 0;
        int right = 0;
        int sum = 0;

        int cnt = 0;
        while (left <= right) {

            if (sum > N){
                sum -= prime.get(left++);
            } else {
                if (sum == N) cnt++;
                if (right == prime.size()) break;
                sum += prime.get(right++);
            }

        }

        System.out.println(cnt);
    }
}