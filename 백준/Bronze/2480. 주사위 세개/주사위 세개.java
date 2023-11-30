import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String array[] = br.readLine().split(" ");
        int A = Integer.parseInt(array[0]);
        int B = Integer.parseInt(array[1]);
        int C = Integer.parseInt(array[2]);

        if (A == B) {
            if (B == C) answer = 10000 + A * 1000;
            else answer = 1000 + A * 100;
        } else if (A == C) {
            answer = 1000 + A * 100;
        } else {
            if (B == C) answer = 1000 + B * 100;
            else answer = Math.max(Math.max(A, B), C) * 100;
        }

        System.out.println(answer);
    }
}