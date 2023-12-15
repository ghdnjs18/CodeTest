import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bufferedReader.readLine());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int A = Integer.parseInt(stringTokenizer.nextToken());

            switch (A) {
                case 1 :
                    int B = Integer.parseInt(stringTokenizer.nextToken());
                    stack.push(B);
                    break;
                case 2 :
                    if (!stack.isEmpty()) {
                        bufferedWriter.write(stack.pop() + "\n");
                    } else {
                        bufferedWriter.write(-1 + "\n");
                    }
                    break;
                case 3 :
                    bufferedWriter.write(stack.size() + "\n");
                    break;
                case 4 :
                    if (stack.isEmpty()) {
                        bufferedWriter.write(1 + "\n");
                    } else {
                        bufferedWriter.write(0 + "\n");
                    }
                    break;
                case 5 :
                    if (!stack.isEmpty()) {
                        bufferedWriter.write(stack.peek() + "\n");
                    } else {
                        bufferedWriter.write(-1 + "\n");
                    }
                    break;
            }
        }

        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}