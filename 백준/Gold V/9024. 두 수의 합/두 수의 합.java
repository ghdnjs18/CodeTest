import java.io.*;
import java.util.*;

public class Main {
    /*
    * 여러개의 테스트 케이스가 주어저 반복한다.
    * 문제에서 주어지는 입력값은 여러 개의 서로 다른 정수 S -> 배열로 사용, 또 다른 정수 K -> 목표 정수로 이용한다.
    * 이분 탐색의 기준은 S배열의 인덱스를 이용하고 K를 이용해 탐색 범위를 조절한다.
    * 서로 다른 두 개의 정수의 합이 K 에 가장 가까운 두 정수를 구하는 것이기 때문에 두 개의 인덱스를 사용해 투 포인트방식으로 탐색이 가능하다.
    * S의 인덱스 0과 마지막을 포인트로 지정하고 합이 가장 가까운 수인지 비교를 하고 횟수를 헤아려준다.
    * 이후, 두 정수의 합을 목표 정수와 비교해 포인트를 이동해준다.
    * */
    static int[] S;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int T = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int N = Integer.parseInt(stringTokenizer.nextToken());
            int K = Integer.parseInt(stringTokenizer.nextToken());
            S = new int[N];
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                S[j] = Integer.parseInt(stringTokenizer.nextToken());
            }
            Arrays.sort(S);

            stringBuilder.append(binarySearch(0, N - 1, K)).append("\n");
        }

        System.out.println(stringBuilder);
    }

    public static int binarySearch(int left, int right, int target) {

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        while (left < right) {
            // 가까운 정수를 갱신해주거나 동일할 경우 횟수를 헤아린다.
            if (min > Math.abs(S[left] + S[right] - target)) {
                min = Math.abs(S[left] + S[right] - target);
                cnt = 1;
            } else if (min == Math.abs(S[left] + S[right] - target)) {
                cnt++;
            }

            // 두 정수의 합을 목표 정수보다 같거나 작으면 left증가, 높으면 right감소
            if (S[left] + S[right] <= target) {
                left++;
            } else if (S[left] + S[right] > target){
                right--;
            }
        }
        return cnt;
    }
}