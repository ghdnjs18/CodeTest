import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= T; i++) {
			String array[] = br.readLine().split(" ");
			int x1 = Integer.parseInt(array[0]);
			int y1 = Integer.parseInt(array[1]);
			int r1 = Integer.parseInt(array[2]);
			int x2 = Integer.parseInt(array[3]);
			int y2 = Integer.parseInt(array[4]);
			int r2 = Integer.parseInt(array[5]);
			double range = Math.sqrt((int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
			int answer = 0;
			
			if  (x1 == x2 && y1 == y2) {
				if (r1 == r2) { // 크기가 같은 동심원
					answer = -1;
				} else { // 크기가 다른 동심원
					answer = 0;
				}
			} else {
				if (r1 + r2 > range) { // 두원의 교점
					if (Math.abs(r1 - r2) < range) {
						answer = 2;
					} else if (Math.abs(r1 - r2) == range) { // 내접
						answer = 1;
					} else { // 원안에 작은원
						answer = 0;
					}
				} else if (r1 + r2 == range) { // 외접
					answer = 1;
				} else { // 멀리 있는 두원
					answer = 0;
				}
				
			}
			
			System.out.println(answer);
		}
	}
}