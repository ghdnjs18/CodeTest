import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(bufferedReader.readLine());

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            String command = stringTokenizer.nextToken();
            switch (command) {
                case "push":
                    queue.offer(Integer.parseInt(stringTokenizer.nextToken()));
                    break;
                case "pop":
                    if (queue.isEmpty()) {
                        bufferedWriter.write(-1 + "\n");
                    } else {
                        bufferedWriter.write(queue.poll() + "\n");
                    }
                    break;
                case "size":
                    bufferedWriter.write(queue.size() + "\n");
                    break;
                case "empty":
                    if (queue.isEmpty()) {
                        bufferedWriter.write(1 + "\n");
                    } else {
                        bufferedWriter.write(0 + "\n");
                    }
                    break;
                case "front":
                    if (queue.isEmpty()) {
                        bufferedWriter.write(-1 + "\n");
                    } else {
                        bufferedWriter.write(queue.peek() + "\n");
                    }
                    break;
                case "back":
                    if (queue.isEmpty()) {
                        bufferedWriter.write(-1 + "\n");
                    } else {
                        bufferedWriter.write(queue.peekLast() + "\n");
                    }
                    break;
            }
        }
        bufferedReader.close();

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}