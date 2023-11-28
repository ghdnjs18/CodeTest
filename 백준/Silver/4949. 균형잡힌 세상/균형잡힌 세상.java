import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while (true) {
			String s[] = br.readLine().split("");
			if (s[0].equals(".")) break;
			
			Boolean check = true;
			List<String> bracket_t = new ArrayList<>();
			for (int i = 0; i < s.length; i++) {
				if (s[i].equals("(")) {
					bracket_t.add("(");
				} else if (s[i].equals(")")) {
					if (bracket_t.isEmpty()) {
						bw.write("no\n");
						check = false;
						break;
					} else if (bracket_t.get(bracket_t.size()-1).equals("(")) {
						bracket_t.remove(bracket_t.size()-1);
					} else {
						bw.write("no\n");
						check = false;
						break;
					}
				} else if (s[i].equals("[")) {
					bracket_t.add("[");
				} else if (s[i].equals("]")) {
					if (bracket_t.isEmpty()) {
						bw.write("no\n");
						check = false;
						break;
					} else if (bracket_t.get(bracket_t.size()-1).equals("[")) {
						bracket_t.remove(bracket_t.size()-1);
					} else {
						bw.write("no\n");
						check = false;
						break;
					}
				} 
			}
            if (!bracket_t.isEmpty() && check) {
				check = false;
				bw.write("no\n");
			}
			if (check) bw.write("yes\n");
            bracket_t.clear();
		}
		bw.flush();
        bw.close();
	}
}