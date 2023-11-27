import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= T; i++) {
			String array[] = br.readLine().split(" ");
			int floor = Integer.parseInt(array[0]);
			int room = Integer.parseInt(array[1]);
			int num = Integer.parseInt(array[2]);
			
			int temp = 0;
			int temp2 = 0;
			if (num % floor == 0) {
				temp = floor;
				temp2 = num / floor;
			} else {
				temp = num % floor;
				temp2 = num / floor + 1;
			}
			System.out.println(temp + String.format("%02d", temp2));						
		}
	}
}