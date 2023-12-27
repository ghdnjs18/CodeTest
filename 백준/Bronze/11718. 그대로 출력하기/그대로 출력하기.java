import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            bufferedWriter.write(bufferedReader.readLine() + "\n");

            if (!bufferedReader.ready()) break;
        }
        
        bufferedReader.close();

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}