import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = 0;
		int answer = 0;
		int cnt = 0;
		
		// n < i <= 2n 의 소수의 개수
		// 반복문을 이용해서 n에서 2n까지 반복하면서 소수를 구한다.
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0) break;
			
			for (int i = n+1; i <= 2*n; i++) {
				cnt = 0;
				
				for (int j = 2; j*j <= i; j++) {
					if (i % j == 0) {
						cnt++;
						break;
					}
				}
				if (cnt == 0) answer++;
			}
			
			// 0일경우 출력이 되지않아야한다.
			if (answer > 0) { 
				System.out.println(answer);
				answer = 0;
			}
			
		}
		
	}

}