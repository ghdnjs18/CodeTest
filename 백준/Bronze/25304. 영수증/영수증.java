import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        
        int cash = 0;
        for (int i = 0; i < N; i++) {
            String array[] = br.readLine().split(" ");
            int a = Integer.parseInt(array[0]);
            int b = Integer.parseInt(array[1]);
            
            cash += a * b;
        }
        
        if (X == cash) System.out.print("Yes");
        else System.out.print("No");
        
    }
}