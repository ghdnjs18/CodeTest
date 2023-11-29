import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());
		int cnt = 0;
		int temp = 0;
		List<Integer> stack = new ArrayList<>();
		
		for (int i = 1; i <= T; i++) {
			StringTokenizer n = new StringTokenizer(br.readLine());
			String num = n.nextToken();
			
			if (cnt < Integer.parseInt(num)) {
				for (int j = cnt + 1; j <= Integer.parseInt(num); j++) {
					stack.add(j);
					sb.append("+\n");
					cnt++;
				}
				sb.append("-\n");
				stack.remove(stack.size()-1);
			} else if (Integer.parseInt(num) < temp && stack.get(stack.size()-1) == Integer.parseInt(num)){
				sb.append("-\n");
				stack.remove(stack.size()-1);
			} else {
				sb.delete(0, sb.length());
				sb.append("NO");
				break;
			}
			temp = Integer.parseInt(num);
		}

		System.out.println(sb.toString());
	}
}