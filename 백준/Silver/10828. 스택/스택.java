import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String command = "";
		String num = "";
		List<String> stack = new ArrayList<>();
		
		for (int i = 1; i <= T; i++) {
			String array[] = br.readLine().split(" ");
			if (array[0].equals("push")) {
				command = array[0];
				num = array[1];
			} else {
				command = array[0];
			}
			
			switch (command) {
			case "push":
				stack.add(num);
				break;
			case "pop":
				if (stack.size() > 0) {
					System.out.println(stack.get(stack.size()-1));
					stack.remove(stack.size()-1);
				} else {
					System.out.println("-1");
				}
				break;
			case "size":
				System.out.println(stack.size());
				break;
			case "empty":
				if (stack.size() > 0) {
					System.out.println("0");
				} else {
					System.out.println("1");
				}
				break;
			case "top":
				if (stack.size() > 0) {
					System.out.println(stack.get(stack.size()-1));
				} else {
					System.out.println("-1");
				}
				break;				
			}
		}
	}
}