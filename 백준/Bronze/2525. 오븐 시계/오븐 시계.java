import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String answer = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String array[] = br.readLine().split(" ");
        int A = Integer.parseInt(array[0]);
        int B = Integer.parseInt(array[1]);
        int C = Integer.parseInt(br.readLine());

        A = A + (B + C) / 60;
        A = A > 23 ? A - 24 : A;
        B = (B + C) % 60;

        System.out.println(A + " " + B);

    }
}