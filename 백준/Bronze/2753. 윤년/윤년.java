import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String answer = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        if ((num % 4 == 0 && num % 100 != 0) || (num % 400 == 0)) {
            answer = "1";
        } else {
            answer = "0";
        }

        System.out.println(answer);

    }
}