import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n-i; j++) {
                stringBuilder.append(" ");
            }
            for (int j = 1; j <= 2*i-1; j++) {
                stringBuilder.append("*");
            }
            stringBuilder.append("\n");
        }
        for (int i = n-1; i >= 1; i--) {
            for (int j = 1; j <= n-i; j++) {
                stringBuilder.append(" ");
            }
            for (int j = 1; j <= 2*i-1; j++) {
                stringBuilder.append("*");
            }
            stringBuilder.append("\n");
        }
        System.out.print(stringBuilder);
    }
}