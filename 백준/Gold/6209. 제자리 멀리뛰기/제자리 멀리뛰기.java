import java.io.*;
import java.util.*;

public class Main {
    
    /*
    * 주어진 값 : 움직이는 거리 D, 돌섬의 수 N, 제거하는 돌섬의 수 M, N개의 시작점에서 돌섬의 거리
    * 조건 : 최소거리의 돌섬 M개를 제거한다.
    * 목표 : M개의 돌섬 제거 후 이동거리의 최댓값
    * 아분탐색을 이용해 최소 거리를 변경하면서 돌섬의 제거 개수를 확인한다.
    * 돌섬의 제거 개수가 많을 경우 최소 거리를 줄이고, 아니면 최소 거리를 늘린다.
    * */
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int D = Integer.parseInt(stringTokenizer.nextToken());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        int[] island = new int[N + 2];
        for (int i = 0; i < N; i++) {
            island[i] = Integer.parseInt(bufferedReader.readLine());
        }

        int left = 0;
        int right = D;

        island[N] = left;
        island[N + 1] = right;
        Arrays.sort(island);

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int now = 0;
            int remove = 0;
            for (int i = 1; i < island.length; i++) {
                if (island[i] - island[now] < mid) {
                    remove++;
                } else {
                    now = i;
                }
            }

            if (remove > M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(right);
    }
}