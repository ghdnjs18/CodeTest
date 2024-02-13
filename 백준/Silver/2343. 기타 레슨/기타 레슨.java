import java.io.*;
import java.util.*;

public class Main {
    /*
    * 강의를 배열에 넣어주고 가장 큰 강의 길이와 강의의 총길이를 구한다. 
    * 강의를 넣은 배열을 오름차순 정렬해준다. x -> 문제의 조건에 정렬을 하면 안된다.
    * 가장 큰 강의와 강의의 총길이 사이에서 이분 탐색을 한다.
    * 이분 탐색을 하면서 중간값을 기준으로 강의를 블루레이에 나눠담는다.
    * 블루레이의 개수가 주어진 개수보다 많으면 left를 키워 블루레이의 크기를 키워 개수를 줄인다.
    * 블루레이의 개수가 주어진 개수와 같거나 적으면 right를 줄여 블루레이의 크기를 줄여 개수를 늘린다.
    * */
    static int[] lecture;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        int sum = 0;
        int max = 0;
        lecture = new int[N];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        // 입력값을 받으면서 가장큰 강의 길이와 강의의 총길이를 구한다.
        for (int i = 0; i < N; i++) {
            lecture[i] = Integer.parseInt(stringTokenizer.nextToken());
            sum += lecture[i];
            max = Math.max(max, lecture[i]);
        }
        bufferedReader.close();

        System.out.println(binarySearch(max, sum, M));
    }

    public static int binarySearch(int left, int right, int target) {

        while (left < right) {
            int mid = left + (right - left) / 2;

            // 블루레이에 강의 나누기
            int total = 0;
            int cnt = 1;
            // 앞부분 부터 잘라서 기준이 되는 블루레이 크기 비교해 넘어가면 다음 블루레이로 초기화해 계산
            for (int i = 0; i < N; i++) {
                if (total + lecture[i] > mid) {
                    cnt++;
                    total = 0;
                }
                total += lecture[i];
            }

            if (cnt > target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}