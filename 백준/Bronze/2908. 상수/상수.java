import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        StringBuilder str = new StringBuilder();
        str.append(stringTokenizer.nextToken());
        int A = Integer.parseInt(String.valueOf(str.reverse()));
        str = new StringBuilder();
        str.append(stringTokenizer.nextToken());
        int B = Integer.parseInt(String.valueOf(str.reverse()));

        System.out.println(Math.max(A, B));
    }
}