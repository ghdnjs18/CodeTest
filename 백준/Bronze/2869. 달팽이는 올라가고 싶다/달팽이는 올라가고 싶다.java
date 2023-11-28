import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		
		String array[] = br.readLine().split(" ");
		int a = Integer.parseInt(array[0]);
		int b = Integer.parseInt(array[1]);
		int v = Integer.parseInt(array[2]);
		
		if (a == v) {
			answer = 1;
		} else {
			answer = (int) Math.ceil((double)(v - a) / (double)(a - b)) + 1;
		}
		
		System.out.println(answer);
		
	}
}