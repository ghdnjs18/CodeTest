import java.io.*;
import java.util.*;

public class Main {

    /*
    * 피보나치 수열을 구하는 재귀함수 방식으로 1과 2의 호출 수를 구할 수 있다.
    * 반복문을 이용해서 입력값과 비교해 1과 2의 값을 구한다.
    * */
    
    static int one, two;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int D = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        // 1과 2의 호출 갯수를 구한다.
        fibonacci(D);

        for (int i = 1; i < K; i++) {
            for (int j = 1; j < K; j++) {
                // 순차적으로 숫자들을 대입해서 타겟 숫자를 출력한다.
                if (i * one + j * two == K) {
                    System.out.println(i);
                    System.out.println(j);
                    return;
                }
                if (i * one + j * two > K) {
                    break;
                }
            }
        }
    }

    public static int fibonacci (int day) {
        if (day == 1) {
            one++;
            return 0;
        }
        if (day == 2) {
            two++;
            return 0;
        }
        return fibonacci(day - 1) + fibonacci(day - 2);
    }
}