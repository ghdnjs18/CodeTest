import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        int[] array = new int[N];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        bufferedReader.close();
        int[] temp = array.clone();
        Arrays.sort(temp);

        int cnt = 0;
        int[] answer = new int[N];
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i] == array[j] && !visited[j]) {
                    visited[j] = true;
                    answer[j] = cnt++;
                    break;
                }
            }
        }

        for (int i : answer) {
            System.out.print(i + " ");
        }

    }
}
