import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 카드 배열 저장
        int N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] cardPic = new int[N];
        for (int i = 0; i < N; i++) {
            cardPic[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(cardPic);

        int M = Integer.parseInt(bufferedReader.readLine());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int cardNum = Integer.parseInt(stringTokenizer.nextToken());

            // 해당 숫자 카드의 낮은 인덱스 위치
            int minStart = 0;
            int end = N;
            while (minStart < end) {
                int mid = minStart + ((end- minStart) / 2);

                if (cardPic[mid] < cardNum) {
                    minStart = mid + 1;
                } else {
                    end = mid;
                }
            }

            // 해당 숫자 카드의 높은 인덱스 위치
            int maxStart = 0;
            end = N;
            while (maxStart < end) {
                int mid = maxStart + ((end- maxStart) / 2);

                if (cardPic[mid] <= cardNum) {
                    maxStart = mid + 1;
                } else {
                    end = mid;
                }
            }
            // 인덱스 차이로 갯수 확인
            stringBuilder.append(maxStart - minStart + " ");
        }
        System.out.print(stringBuilder);
    }
}