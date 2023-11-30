import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String answer = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());
        if (x > 0 && y > 0) {
            answer = "1";
        } else if (x > 0 && y < 0) {
            answer = "4";
        } else if (x < 0 && y > 0) {
            answer = "2";
        } else if (x < 0 && y < 0) {
            answer = "3";
        }

        System.out.println(answer);

    }
}