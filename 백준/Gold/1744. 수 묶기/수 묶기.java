import java.io.*;
import java.util.*;

public class Main {
    /**
     * 주어진 값 : N개의 숫자 M (0 < N <= 50, -1000 <= M <= 1000)
     * 조건 : 숫자를 위치에 상관없이 묶어 곱할 수 있다. -> 정렬가능
     * 목표 : 수의 합의 최댓값
     * 양수와 음수가 서로 곱하면 음수가 되므로 양수와 음수를 배열을 나눠 수를 정렬을 해준다.
     * 높은 수를 먼저 묶어 숫자간 곱한 값이 더한 값보다 크면 곱한다.
     * 마지막 숫자가 묶이지 않았을 경우, 비교할 범위가 배열을 넘어가는 것을 주의하고 수를 더해줘야한다.
     */
    public static void main(String[] args) throws IOException {
        // 숫자의 개수
        int N = readNumber();

        // 숫자 정보 입력
        List<Integer> plus = new ArrayList<>();
        List<Integer> minus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int num = readNumber();

            if (num > 0) {
                plus.add(num);
            } else {
                minus.add(num);
            }
        }

        // 양수는 내림차순, 음수는 오름차순으로 정렬해 절대값이 큰 순으로 정렬을 해준다.
        plus.sort((o1, o2) -> o2 - o1);
        minus.sort((o1, o2) -> o1 - o2);

        // 양수 음수를 정렬한 값을 하나로 합쳐준다.
        List<Integer> numbers = new ArrayList<>();
        numbers.addAll(plus);
        numbers.addAll(minus);

        // 비교하면서 최댓값을 더해준다.
        int result = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (i == numbers.size() - 1) {
                result += numbers.get(i);
                break;
            }
            if (numbers.get(i) + numbers.get(i + 1) <= numbers.get(i) * numbers.get(i + 1)) {
                result += numbers.get(i) * numbers.get(i + 1);
                i++;
            } else {
                result += numbers.get(i);
            }
        }

        System.out.println(result);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        return flag ? -cur : cur;
    }
}