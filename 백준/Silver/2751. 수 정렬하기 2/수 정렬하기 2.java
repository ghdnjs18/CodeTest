import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bufferedReader.readLine());

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(bufferedReader.readLine()));
        }
        bufferedReader.close();
        list.sort((o1, o2) -> o1 - o2);

        for (Integer i : list) {
            bufferedWriter.write(i + "\n");
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}