import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int answer = 0;
		List<String> stack = new ArrayList<>();
		
		for (int i = 1; i <= K; i++) {
			String num = br.readLine();
			
			if (num.equals("0") && stack.size() > 0) {
				stack.remove(stack.size()-1);
			} else {
				stack.add(num);
			}
		}
		
		for (int i = 0; i < stack.size(); i++) {
			answer += Integer.parseInt(stack.get(i));
		}
		
		System.out.println(answer);
	}
}