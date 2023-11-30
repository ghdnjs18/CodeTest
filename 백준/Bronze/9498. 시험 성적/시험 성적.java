import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String answer = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        switch (num / 10) {
            case 10:
            case 9: answer = "A"; break;
            case 8: answer = "B"; break;
            case 7: answer = "C"; break;
            case 6: answer = "D"; break;
            default: answer = "F";
        }

        System.out.println(answer);
    }
}