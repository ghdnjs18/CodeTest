import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // N의 가장 뒤 두 자리를 가장 작게 만들려면 00으로 만들어야 한다.
        int N = Integer.parseInt(bufferedReader.readLine()) / 100 * 100;
        int F = Integer.parseInt(bufferedReader.readLine());

        // N이 F로 나눠질 때까지 N을 올리고 같이 출력값을 올려준다.
        int result = 0;
        while (N % F != 0) {
            N++;
            result++;
        }

        // 출력값이 한자리면 0을 붙여주고 두자리면 출력한다.
        if (result < 10) {
            System.out.println("0" + result);
        } else {
            System.out.println(result);
        }
    }
}
