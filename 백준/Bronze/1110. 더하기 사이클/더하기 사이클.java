import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();
		int cnt = 0;
		
		if (Integer.parseInt(N) < 10) { 
			N = String.format("%02d", Integer.parseInt(N));
		}
        
		String temp = N;
        if (N.equals("0")) {
			System.out.println(1);
		} else {
			while (true) {
				if (Integer.parseInt(temp.substring(0, 1)) + Integer.parseInt(temp.substring(1, 2)) < 10) {
					temp = temp.substring(1, 2) + Integer.toString(Integer.parseInt(temp.substring(0, 1)) + Integer.parseInt(temp.substring(1, 2)));
				} else {
					temp = temp.substring(1, 2) + Integer.toString(Integer.parseInt(temp.substring(0, 1)) + Integer.parseInt(temp.substring(1, 2))).substring(1, 2); 
				}
				cnt++;
				
				if (temp.equals(N)) break;
			}
			
			System.out.println(cnt);
		}
	}
}