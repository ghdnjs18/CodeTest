import java.io.*;
import java.util.*;

public class Main {
    /*
    * N은 항상 짝수로 나오기 때문에 석순과 종유석에 묵어서 사용할 수 있다.
    * 석순과 종유석을 각각 배열로 사용해서 이분탐색을 사용한다.
    * 높이를 순차적으로 반복을 하면서 해당 높이에 해당하는 석순과 종유석을 찾는다.
    * 석순은 밑에서 부터 높이를 비교해서 개수를 헤아린다.
    * 종유석은 위에서 부터 높이를 비교해서 개수를 헤어려 석순의 개수와 합친다.
    * 장애물의 최솟값과 같으면 횟수를 증가하고 쵯솟값이 더 작으면 갱신해준다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // N은 항상 짝수 이다.
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int H = Integer.parseInt(stringTokenizer.nextToken());

        // 석순과 종유석을 한쌍으로 나오기 때문에 반복을 줄일 수 있다.
        int[] bot = new int[N / 2];
        int[] top = new int[N / 2];
        for (int i = 0; i < N / 2; i++) {
            bot[i] = Integer.parseInt(bufferedReader.readLine());
            top[i] = Integer.parseInt(bufferedReader.readLine());
        }
        bufferedReader.close();
        Arrays.sort(bot);
        Arrays.sort(top);

        int min = N;
        int cnt = 0;
        for (int i = 1; i <= H; i++) {
            int left = 0;
            int right = N / 2;
            int wall = 0;
            // 해당 높이의 석순 헤아리기
            while (left < right) {
                int mid = left + (right - left) / 2;

                // 해당 높이에 지나는 석순의 수 출력
                if (bot[mid] >= i) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            wall += bot.length - right;

            left = 0;
            right = N / 2;
            // 해당 높이의 종유석 헤아리기
            while (left < right) {
                int mid = left + (right - left) / 2;

                // 종유석은 위에서 시작하기 때문에 높이를 줄여가며 비교한다.
                if (top[mid] >= H - i + 1) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            wall += top.length - right;

            // 장애물의 최솟값과 같으면 횟수를 증가하고 쵯솟값이 더 작으면 갱신해준다.
            if (min == wall) {
                cnt++;
            } else if (min > wall) {
                min = wall;
                cnt = 1;
            }
        }

        System.out.println(min + " " + cnt);
    }
}