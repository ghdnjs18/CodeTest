import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        String[][] board = new String[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = bufferedReader.readLine().split("");
        }

        int answer = Integer.MAX_VALUE;
        for (int x = 0; x < N - 7; x++) {
            int temp = Integer.MAX_VALUE;
            for (int y = 0; y < M - 7; y++) {
                int firstBCnt = 0;
                int firstWCnt = 0;

                for (int i = x; i < x+8; i++) {
                    for (int j = y; j < y+8; j++) {
                        if ((i + j) % 2 == 1) {
                            if (board[i][j].equals("W")) firstWCnt++;
                            if (board[i][j].equals("B")) firstBCnt++;
                        }
                        if ((i + j) % 2 == 0) {
                            if (board[i][j].equals("W")) firstBCnt++;
                            if (board[i][j].equals("B")) firstWCnt++;
                        }
                    }
                }
                temp = Math.min(Math.min(firstWCnt, firstBCnt), temp);
            }
            answer = Math.min(answer, temp);
        }
        System.out.println(answer);
    }
}