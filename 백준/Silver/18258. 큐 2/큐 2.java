import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		Deque<String> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= T; i++) {
			StringTokenizer command = new StringTokenizer(br.readLine());
			
			switch (command.nextToken()) {
			case "push":
				queue.add(command.nextToken());
				break;
			case "pop":
				if (queue.isEmpty()) {
					bw.write("-1\n");
				} else {
					bw.write(queue.poll()+"\n");
				}
				break;
			case "size":
				bw.write(queue.size()+"\n");
				break;
			case "empty":
				if (queue.isEmpty()) {
					bw.write("1\n");
				} else {
					bw.write("0\n");
				}
				break;
			case "front":
				if (queue.isEmpty()) {
					bw.write("-1\n");
				} else {
					bw.write(queue.peekFirst()+"\n");
				}
				break;	
			case "back":
				if (queue.isEmpty()) {
					bw.write("-1\n");
				} else {
					bw.write(queue.peekLast()+"\n");
				}
				break;
			}
		}
		
		bw.flush();
		bw.close();
	}
}