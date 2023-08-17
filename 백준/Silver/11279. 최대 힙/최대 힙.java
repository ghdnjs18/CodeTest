import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        int x = Integer.parseInt(br.readLine());

        for (int i = 0; i < x; i++) {
            int temp = Integer.parseInt(br.readLine());
            if (temp > 0) {
                queue.add(temp);
            } else {
                if (!queue.isEmpty()) {
                    bw.write(queue.poll() + "\n");
                } else {
                    bw.write("0\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }
}