import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String answer = "";
//        Scanner sc = new Scanner(System.in);
//        int M = sc.nextInt();
//        int N = sc.nextInt();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String array[] = br.readLine().split(" ");
        int M = Integer.parseInt(array[0]);
        int N = Integer.parseInt(array[1]);

        answer = M > N ? ">": M < N ? "<" : "==";

        System.out.println(answer);
    }
}