import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
        
		for (int i = 0; i < T; i++) {
			String bracket[] = br.readLine().split("");
			int left = 0;
			int right = 0;
			int cnt = 0;
			
			for (int j = 0; j < bracket.length; j++) {
				if (bracket[j].equals("(")) {
					left++;
					cnt++;
				}
				else {
					right++;
					cnt--;
				}
				if (cnt < 0) break;
			}
			
			if (left != right) bw.write("NO\n");
			else if (cnt < 0) bw.write("NO\n");
			else bw.write("YES\n");
		}
		
		bw.flush();
		bw.close();
	}
}