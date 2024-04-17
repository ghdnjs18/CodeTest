import java.io.IOException;

public class Main {
    /*
    * 주어진 값 : 번호의 개수 N, 가진 돈 M, 번호의 가격 N개
    * 목표 : 가진 돈으로 만들 수 있는 가장큰 숫자 조합
    * 가장큰 숫자의 조합을 만들기 위해서는 숫자의 개수가 가장 많아야한다.
    * -> 가장 싼 숫자로 최대 숫자의 개수를 알 수 있다. (가장 싼 숫자로 숫자 조합을 만든다.)
    * 만들어진 숫자 조합에서 앞자리부터 가장 큰 숫자 부터 구매할 수 있는지 확인해 구매가 가능하면 숫자를 교체해준다.
    * 숫자 조합의 첫자리가 0일 수 없기 때문에 해당 구매 비용을 반환해준다.
    * */

    public static void main(String[] args) throws IOException {
        // 문방구에서 파는 숫자 범위
        int N = readNumber();

        // 숫자의 가격
        int[] price = new int[N];
        int minPrice = 50;
        int minindex = 0;
        for (int i = 0; i < N; i++) {
            price[i] = readNumber();

            if (price[i] <= minPrice) {
                minPrice = price[i];
                minindex = i;
            }
        }

        // 가지고 있는 금액
        int M = readNumber();

        // 하나의 숫자의 최소 비용이 가지고 있는 금액과 같으면 해당 숫자를 출력한다.
        if (M == minPrice) {
            System.out.println(minindex);
            return;
        }

        int[] numbers = new int[M + 1];
        int cnt = 0;
        // 가지고 있는 금액으로 가장 많은 숫자를 구매할 수 있는 개수를 구한다.
        while (M >= minPrice) {
            M -= minPrice;
            numbers[cnt++] = minindex;
        }

        int start = 0;
        for (int i = 0; i < cnt; i++) {
            // 가장 큰 수부터 비교해 구매가 가능하면 교체를 해준다.
            for (int j = N - 1; j >= 0; j--) {
                if (price[j] <= M + minPrice) {
                    M += minPrice - price[j];
                    numbers[i] = j;
                    break;
                }
            }
            // 첫 자리에는 0이 올 수 없다.
            if (numbers[start] == 0) {
                start++;
                M += minPrice;
            }
        }
        
        if (start == cnt) {
            System.out.println(0);
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i < cnt; i++) {
            stringBuilder.append(numbers[i]);
        }
        System.out.print(stringBuilder);
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