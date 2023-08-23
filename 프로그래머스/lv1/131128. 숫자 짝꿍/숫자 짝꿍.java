import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public String solution(String X, String Y) {
        // 정렬한 문자열을 뒤집어서 출력하기 위해 사용
        StringBuilder answer = new StringBuilder();
        List<Integer> pair = new ArrayList<>();

        // X와 Y를 정렬하여 사용한다.
        char[] x = X.toCharArray();
        char[] y = Y.toCharArray();
        Arrays.sort(x);
        Arrays.sort(y);
        X = new String(x);
        Y = new String(y);

        // 짝꿍의 수를 구한다.
        int sum = 0;
        for (int i = 0; i < 10; i++) { // 0~9까지의 짝궁의 수를 구한다.
            int x_fir = X.indexOf(String.valueOf(i)); // 없으면 -1 출력
            int x_las = X.lastIndexOf(String.valueOf(i));
            int y_fir = Y.indexOf(String.valueOf(i));
            int y_las = Y.lastIndexOf(String.valueOf(i));
            // -1일경우 숫자가 없고, 첫 번째 인덱스와 마지막 인덱스가 같은 경우 한 개이고,
            // 나머지는 마지막 인덱스 - 첫 번째 인덱스 + 1을 하면 해당 글자를 가지고 있는 수이다
            int x_len = x_las == -1 ? 0 : x_las == x_fir ? 1 : x_las - x_fir + 1;
            int y_len = y_las == -1 ? 0 : y_las == y_fir ? 1 : y_las - y_fir + 1;

            // 비교 하여 작은 수 만큼 짝꿍이 될 수 있다.
            pair.add(x_len > y_len? y_len:x_len);
            sum += pair.get(i);
        }

        if (sum == 0) { // 짝꿍이 한명도 없으면 -1
            answer.append("-1");
        } else if (sum == pair.get(0)) { // 짝꿍의 수가 0의 수이면 전부 0
            answer.append("0");
        } else {
            int cnt = 0;
            while (pair.iterator().hasNext()) { // 해당 숫자의 짝꿍 수만큼 문자열 생성
                answer.append(String.valueOf(cnt++).repeat(pair.iterator().next()));
                pair.remove(0);
            }
        }
        // -1을 제외하고 뒤집어서 정렬해준다.
        return answer.toString().equals("-1")? answer.toString():answer.reverse().toString();
    }
}