import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        bufferedReader.close();

        int[][] stairs = new int[N + 1][10];

        // 한 자릿수는 0을 제외한 모두 1개씩 있다.
        for(int i = 1; i <= 9; i++) {
            stairs[1][i] = 1;
        }

        for(int i = 2; i <= N; i++) {
            for(int j = 0; j <= 9; j++) {
                // 0은 1일 경우에만 나올 수 있다.
                if(j == 0) {
                    stairs[i][0] = stairs[i - 1][1] % 1000000000;
                }
                // 9는 8일 경우에만 나올 수 있다.
                else if (j == 9) {
                    stairs[i][9] = stairs[i - 1][8] % 1000000000;
                }
                // 그 외의 경우 이전 자릿수의 양쪽의 값을 더한다.
                else {
                    stairs[i][j] = (stairs[i - 1][j - 1] + stairs[i - 1][j + 1]) % 1000000000;
                }
            }
        }

        long answer = 0;
        for(int i = 0; i < 10; i++) {
            answer += stairs[N][i];
        }

        System.out.println(answer % 1000000000);
    }
}