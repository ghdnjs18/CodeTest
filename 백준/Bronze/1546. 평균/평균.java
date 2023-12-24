import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        Integer[] subject = new Integer[N];
        for (int i = 0; i < N; i++) {
            int score = Integer.parseInt(stringTokenizer.nextToken());
            subject[i] = score;
        }

        Arrays.sort(subject, (o1, o2) -> o2 - o1);

        double sum = 0;
        double max = subject[0];
        for (Integer i : subject) {
            sum += i / max * 100;
        }

        System.out.println(sum / N);
    }
}