import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String S = bufferedReader.readLine();
        int N = Integer.parseInt(bufferedReader.readLine());

        System.out.println(S.charAt(N-1));
    }
}