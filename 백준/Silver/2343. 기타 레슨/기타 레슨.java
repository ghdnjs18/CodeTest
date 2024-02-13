import java.io.*;
import java.util.*;

public class Main {
    /*
    * 강의를 배열에 넣어주고 강의의 총합을 가지고 블루레이가 될 수 있는 최대 크기를 설정한다.
    * 강의를 넣은 배열을 오름차순 정렬해준다.
    * 가장 큰 강의와 최대 블루레이 크기의 사이에서 이분 탐색을 한다.
    * 이분 탐색을 하면서 중간값을 기준으로 강의를 블루레이에 나눠담는다.
    * */
    static int[] lectures;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        lectures = new int[N];
        int sum = 0;
        int maxBlueray = 0;
        for (int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(stringTokenizer.nextToken());
            sum += lectures[i];
            if (maxBlueray < lectures[i])
                maxBlueray = lectures[i];
        }
        System.out.println(lowerBound(maxBlueray, sum, M));
    }

    public static int lowerBound(int start, int end, int target) {
        while (start < end){
            int mid = (start + end) / 2;
            if (getCount(mid) > target)
                start = mid + 1;
            else
                end = mid;
        }
        return start;
    }

    private static int getCount(int mid) {
        int count = 1;
        int remain = mid;
        for (int i = 0; i < N; i++) {
            if (remain < lectures[i]) {
                remain = mid;
                count++;
            }
            remain -= lectures[i];
        }
        return count;
    }
}