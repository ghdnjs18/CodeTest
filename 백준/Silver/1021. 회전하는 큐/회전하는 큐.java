import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer Q = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(Q.nextToken());
		int M = Integer.parseInt(Q.nextToken());
		Deque<Integer> queue = new ArrayDeque<>();
		int select = 0;
		int cnt = 0;
		
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}
		
		Q = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			select = Integer.parseInt(Q.nextToken());
			
			if (queue.peek() == select) {
				queue.pop();
			} else {
				Iterator<Integer> it = queue.iterator();
				Iterator<Integer> itr = queue.descendingIterator();
				int left = 0;
				int right = 1;
				
				while (it.hasNext()) {
					if (it.next() == select) break;
					left++;
		        }
				while (itr.hasNext()) {
					if (itr.next() == select) break;
					right++;
		        }
				
				if (left <= right) {
					for (int j = 0; j < left; j++) {
						queue.addLast(queue.pollFirst());
						cnt++;
					}
					queue.pollFirst();
				} else {
					for (int j = 0; j < right; j++) {
						queue.addFirst(queue.pollLast());
						cnt++;
					}
					queue.pollFirst();
				}
			}
		}
		
		System.out.println(cnt);
	}
}