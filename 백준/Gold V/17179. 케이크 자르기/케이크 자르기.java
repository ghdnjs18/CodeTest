import java.io.*;
import java.util.*;

public class Main {
    /*
    * 롤 케이크의 길이 L, 케이크를 자를 수 있는 지점의 개수 M와 위치들 S, 자르는 최대 길이 수 N과 길이들 Q
    * S와 Q는 오름차순으로 주어지고 중복이 없어, 정렬을 할 필요가 없다.
    * 이분 탐색의 기준은 롤 케이크의 길이로 하고 0부터 롤 케이크의 길이 사이에서 탐색한다.
    * 탐색 반복을 하면서 지점 별로 케이크를 자르면서 크기를 비교해 조각을 나눈다.
    * 조각의 개수를 비교해 최소 크기중 최대 크기를 출력한다.
    * */

    static int[] point;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int cutCount = Integer.parseInt(stringTokenizer.nextToken());
        int pointCount = Integer.parseInt(stringTokenizer.nextToken());
        int cakeLength = Integer.parseInt(stringTokenizer.nextToken());

        // 케이크 자르는 지점 입력, 마지막 지점을 자르고 남은 케이크 크기를 알기 위해 마지막에 케이크 길이를 추가해준다.
        point = new int[pointCount + 1];
        for (int i = 0; i < pointCount; i++) {
            point[i] = Integer.parseInt(bufferedReader.readLine());
        }
        point[pointCount] = cakeLength;

        // 주어진 조각수로 이분 탐색
        for (int i = 0; i < cutCount; i++) {
            int cut = Integer.parseInt(bufferedReader.readLine());
            stringBuilder.append(binarySearch(0, cakeLength, cut)).append("\n");
        }

        System.out.println(stringBuilder);
    }

    public static int binarySearch(int left, int right, int target) {
        int answer = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;

            // 케이크 조각 나누기
            int prev = 0;
            int cnt = 0;
            for (int cut : point) {
                if (cut - prev >= mid) {
                    cnt++;
                    prev = cut;
                }
            }

            if (cnt > target) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid;
            }
        }
        return answer;
    }
}