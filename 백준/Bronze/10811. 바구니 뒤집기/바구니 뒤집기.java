import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        // 바구니 배열 초기화
        int[] basket = new int[N];
        for (int i = 0; i < N; i++) {
            basket[i] = i+1;
        }

        // 주어진 숫자 만큼 맨앞과 맨뒤의 배열의 위치를 교체
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken())-1;
            int end = Integer.parseInt(stringTokenizer.nextToken())-1;

            for (int j = start; j < end; j++) {
                int temp = basket[j];
                basket[j] = basket[end];
                basket[end--] = temp;
            }
        }

        for (int i : basket) {
            System.out.print(i + " ");
        }
    }
}