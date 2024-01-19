import java.io.*;

public class Main {
    /*
    * +. - 연산으로 최솟값을 만들기 위해서는 더할 수 있는 부분은 다 더하고 뺄셈을 해야한다.
    * 먼저 입력 문자열을 -를 기준으로 자르고 잘린 문자열을 전부 더해준다.
    * 뺄셈을 기준으로 문자열을 잘랐기 때문에 처음 숫자만 양수가 되고 나머지는 뺄셈을 해 최솟값을 구한다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str = bufferedReader.readLine();

        // 뺄셈을 기준으로 문자열을 자른다.
        String[] numbers = str.split("-");

        int total = 0;
        for (int i = 0; i < numbers.length; i++) {
            // 문자열을 + 기준으로 자르고 전부 더해준다.
            String[] split = numbers[i].split("\\+");
            int sum = 0;
            for (String s : split) {
                sum += Integer.parseInt(s);
            }
            // 처음 숫자만 양수로 기준이되고 나머지는 뺄셈
            if (i == 0) {
                total = sum;
            } else {
                total -= sum;
            }
        }

        System.out.println(total);
    }
}