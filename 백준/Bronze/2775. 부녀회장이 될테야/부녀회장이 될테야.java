import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(bufferedReader.readLine());

        // T만큼 반복
        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(bufferedReader.readLine());
            int N = Integer.parseInt(bufferedReader.readLine());

            // 필요 호 만큼의 아파트 생성
            int[][] apartment = new int[K+1][N+1];
            for (int j = 1, floor = 1; j <= N; j++) {
                // 1호는 무조건 1명
                if (j == 1) apartment[floor][j] = 1;
                // 0층은 j당 한명이므로 1층은 전호수 + j
                else if (floor == 1) apartment[floor][j] = j + apartment[floor][j-1];
                // 해당 호 = 전 호수 + 해당 호 아래층
                else apartment[floor][j] = apartment[floor-1][j] + apartment[floor][j-1];

                if (j == N && floor != K) {
                    j = 0;
                    floor++;
                }
            }
            bufferedWriter.write(apartment[K][N] + "\n");
        }
        bufferedReader.close();

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}