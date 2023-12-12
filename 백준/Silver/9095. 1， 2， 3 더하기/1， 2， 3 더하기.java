import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(bufferedReader.readLine());

        // T만큼 반복
        for (int i = 0; i < T; i++) {
            int answer = 0;
            int x = 0;
            int y = 0;
            int z = 1;

            // 주어진 수를 세 변수를 이용해서 타뷸레이션 방식 활용
            int N = Integer.parseInt(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                answer = x + y + z;
                x = y;
                y = z;
                z = answer;
            }

            bufferedWriter.write(answer + "\n");
        }
        bufferedReader.close();

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}