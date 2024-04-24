import java.io.*;
import java.util.*;

public class Main {
    
    /*
    * 주어진 값 : 움직이는 거리 D, 돌섬의 수 N, 제거하는 돌섬의 수 M, N개의 시작점에서 돌섬의 거리
    * 조건 : 최소거리의 돌섬 M개를 제거한다.
    * 목표 : M개의 돌섬 제거 후 이동거리의 최댓값
    * */
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int D = Integer.parseInt(stringTokenizer.nextToken());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        int[] stone = new int[N + 2];
        for (int i = 0; i < N; i++) {
            stone[i] = Integer.parseInt(bufferedReader.readLine());
        }

        int left = 0;
        int right = D;

        stone[N] = left;
        stone[N + 1] = right;
        Arrays.sort(stone);

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int sum = 0;
            int now = 0;

            for (int i = 1; i < stone.length; i++) {
                if (stone[i] - stone[now] < mid) {
                    sum++;
                } else {
                    now = i;
                }
            }

            if (sum > M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(right);
    }
}