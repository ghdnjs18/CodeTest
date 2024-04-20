import java.io.*;
import java.util.*;

public class Main {

    /*
     * 주어진 값 : 단어의 개수 N, N개의 알파벳 문자열
     * 조건 : 알파벳 대문자를 0부터 9까지 숫자로 바꾼다, 두 개 이상의 알파벳이 같은 숫자로 안 바뀐다. 알파벳은 최대 10개 나온다.
     * 목표 : 주어진 단어의 합의 최댓값
     * 단어의 위치를 수의 자릿수로 치환해서 알파벳 별로 나온 자릿수를 구한다.
     * 나온 알파벳을 정렬해 높은 값부터 9를 곱하면서 0까지 곱해 나온 자릿수 별로 수를 대체해준다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        int[] alpha = new int[26];
        for (int i = 0; i < N; i++) {
            String str = bufferedReader.readLine();

            int temp = (int) Math.pow(10, str.length() - 1);
            for (int j = 0; j < str.length(); j++) {
                alpha[str.charAt(j) - 'A'] += temp;
                temp /= 10;
            }
        }

        Arrays.sort(alpha);

        int result = 0;
        for (int i = alpha.length - 1, num = 9; num > 0; i--, num--) {
            result += alpha[i] * num;
        }

        System.out.println(result);
    }
}