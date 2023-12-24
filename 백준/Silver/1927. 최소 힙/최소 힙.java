import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bufferedReader.readLine());

        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(bufferedReader.readLine());

            if (number != 0) {
                queue.offer(number);
                continue;
            }

            if (queue.isEmpty()) {
                bufferedWriter.write(0 + "\n");
            } else {
                bufferedWriter.write(queue.poll() + "\n");
            }
        }
        bufferedReader.close();

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}