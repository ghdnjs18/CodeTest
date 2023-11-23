import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String array[] = br.readLine().split(" ");
		int M = Integer.parseInt(array[0]);
		int N = Integer.parseInt(array[1]);
		boolean range[] = new boolean[N+1];
		
		range[1] = true;
		
		
		for (int i = 2; i <= N; i++) {
			if (range[i]) continue;
			for (int j = i + i; j <= N; j += i) {
				range[j] = true;
			}
		}
		
		for (int i = 1; i < M; i++) {
			range[i] = true;
		}
		
		for (int i = 2; i <= N; i++) {
			if (!range[i]) System.out.println(i);
		}
	}
}