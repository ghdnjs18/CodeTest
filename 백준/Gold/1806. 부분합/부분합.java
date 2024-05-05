import java.io.*;
import java.util.*;

public class Main {

    /**
     * 주어진 값 : 수열의 개수 N, 목표 수 S, N개의 수열 원소
     * 목표 : 목표 수와 동일한 최소 수열의 연속된 원소합
     * 수열의 인덱스를 투 포인트로 이용해서 연속된 원소들로 목표를 구할 수 있는지 확인한다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 수열의 개수
        int N = Integer.parseInt(stringTokenizer.nextToken());
        // 목표 합계
        int S = Integer.parseInt(stringTokenizer.nextToken());

        // 수열의 정보 입력
        int[] sequence = new int[N + 1];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int sum=0;
        int left=0;
        int right=0;
        int ans = Integer.MAX_VALUE;
        int leng =0;
        while(right <= N) {
            if(sum >= S) { // 합이 s 이상!
                sum -= sequence[left++];
                leng = right - left + 1; // 길이를 구하기
                if(ans > leng) ans = leng; // 길이의 최솟값
            }else if(sum < S) {
                sum += sequence[right++];
            }
        }
        System.out.println((ans) == Integer.MAX_VALUE ? 0 : ans);
    }

}