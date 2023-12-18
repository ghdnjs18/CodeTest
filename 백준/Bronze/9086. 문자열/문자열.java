import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < T; i++) {
            String str = bufferedReader.readLine();
            bufferedWriter.write(String.valueOf(str.charAt(0)) + str.charAt(str.length()-1) + "\n");
        }
        bufferedReader.close();

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}