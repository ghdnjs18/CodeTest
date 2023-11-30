import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String answer = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String array[] = br.readLine().split(" ");
        int H = Integer.parseInt(array[0]);
        int M = Integer.parseInt(array[1]);
        
        H = M - 45 >= 0 ? H : H - 1 < 0 ? 23 : H - 1;
        M = M - 45 >= 0 ? M - 45 : M + 15;

        System.out.println(H + " " + M);

    }
}