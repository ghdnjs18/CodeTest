import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bufferedReader.readLine());
        int cnt = 0;
        for (int i = 1; i <= T; i++) {
            if (i < 100) {
                cnt++;
                continue;
            }
            int hundredDistance = (i / 10 % 10) - (i % 10);
            int tenDistance = (i / 100) - (i / 10 % 10);
            if (hundredDistance == tenDistance) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}