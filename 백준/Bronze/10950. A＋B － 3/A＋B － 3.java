import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int num = Integer.parseInt(br.readLine());

        for (int i = 0; i < num; i++) {
            String array[] = br.readLine().split(" ");
            int A = Integer.parseInt(array[0]);
            int B = Integer.parseInt(array[1]);
            bw.write((A + B) + "\n");
        }

        bw.flush();
        bw.close();
    }
}