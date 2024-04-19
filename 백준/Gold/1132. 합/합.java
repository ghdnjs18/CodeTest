import java.io.*;
import java.util.*;

public class Main {
    
    /*
    * 주어진 값 : 숫자의 개수 N, N개의 알파벳 문자열
    * 조건 : 알파벳 A부터 J에 0부터 9의 숫자를 대입해 수를 만든다, 가장 앞에 오는 알파벳은 0이 될 수 없다.
    * 목표 : 알파벳을 숫자로 만들어 합친 수의 최댓값
    * 알파벳의 위치가 가장 큰 경우 가장 높은 숫자로 되야하기 때문에 입력되는 알파벳의 자릿수를 이용해 해당 알파벳이 나오는 총 자릿수를 구한다.
    * 모든 알파벳을 사용했을 경우, 첫 번째 자리에 나오지 않은 알파벳중 가장 작은 값을 0으로 만든다.
    * 알파벳의 나온 수를 오름차순으로 정렬해 낮은 값부터 0부터 곱하면서 나올 수 있는 최댓값을 구한다.
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        long[] alpha = new long[10];
        boolean[] zero = new boolean[10];
        for (int i = 0; i < N; i++) {
            String str = bufferedReader.readLine();

            // 첫 번째 자리에 있는 알파벳은 0이 되면 안된다.
            zero[str.charAt(0) - 'A'] = true;

            long temp = 1;
            // 끝자리 부터 해당 알파벳에 해당 자리값만큼 더해준다.
            for (int j = str.length() - 1; j >= 0; j--) {
                alpha[str.charAt(j) - 'A'] += temp;
                temp *= 10;
            }
        }

        // 사용하지 않은 알파벳이 있는지 확인
        boolean iszero = false;
        for (int i = 0; i < 10; i++) {
            if (alpha[i] == 0) {
                iszero = true;
            }
        }
        
        // 모든 알파벳을 사용했으면, 그중 가장 작은 수를 찾아 0으로 만든다.
        if (!iszero) {
            long min = Long.MAX_VALUE;
            int idx = 0;
            for (int i = 0; i < 10; i++) {
                if (!zero[i] && alpha[i] < min) {
                    min = alpha[i];
                    idx = i;
                }
            }
            // 가장 작은 수를 0으로 만든다.
            alpha[idx] = 0L;
        }

        // 알파벳을 오름차순으로 정렬한다.
        Arrays.sort(alpha);
        long result = 0;
        for (int i = 0; i < 10; i++) {
            result += alpha[i] * i;
        }
        System.out.println(result);
    }
}