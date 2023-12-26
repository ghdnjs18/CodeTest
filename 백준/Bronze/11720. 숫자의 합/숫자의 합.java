import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());

        int sum = 0;
        String[] numbers = bufferedReader.readLine().split("");
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(numbers[i]);
            sum += N;
        }

        System.out.println(sum);
    }
}