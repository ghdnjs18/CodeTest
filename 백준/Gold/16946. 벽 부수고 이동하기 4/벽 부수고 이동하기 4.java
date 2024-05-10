import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
	static int arr[][];
	static Point ans[][];
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static boolean visited[][];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][M];
		ans = new Point[N][M];
		arr = new int[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				arr[i][j] = str.charAt(j) -'0';
				ans[i][j] = new Point(0,0);
			}
		}
		
		int num = 2;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 0 && !visited[i][j]) {
					bfs(i,j, num++);
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 1) {
					check(i,j);
				}else {
					sb.append(0);
				}
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	/* 4방향 조사 */
	public static void check(int x, int y) {
		HashSet<Integer> dict = new HashSet<>();
		int cnt = 1;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || ny <0 || nx >=N || ny >= M) continue;
			/* 0이면서 다른 묶음일 경우에만 더해줌 */
			if(arr[nx][ny] == 0 && !dict.contains(ans[nx][ny].y)) {
				cnt += ans[nx][ny].x;
				dict.add(ans[nx][ny].y);
			}
		}
		
		sb.append(cnt%10);
	}
	
	/* 0을 개수를 찾는 함수 */
	public static void bfs(int x, int y, int num) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x,y));
		
		/* 0 묶음 좌표 저장할 큐 */
		Queue<Point> temp = new LinkedList<>();
		temp.add(new Point(x,y));
		
		int cnt = 1;	// 0의 개수 count (시작점 포함해서 1)
		visited[x][y] = true;
		
 		while(!q.isEmpty()) {
 			Point cur = q.poll();
 			for(int i=0;i<4; i++) {
 				int nx = cur.x + dx[i];
 				int ny = cur.y + dy[i];
 				
 				if(nx < 0 || ny <0 || nx >=N || ny >=M ) continue;
 				
 				if(!visited[nx][ny] && arr[nx][ny] == 0) {
 					visited[nx][ny] = true;
 					q.add(new Point(nx,ny));
 					temp.add(new Point(nx,ny));
 					cnt++;
 				}
 			}
		}
 		
 		/* 0묶음 개수 표시 */ 
 		while(!temp.isEmpty()) {
 			Point cur = temp.poll();
 			ans[cur.x][cur.y].x = cnt;	// 개수	
 			ans[cur.x][cur.y].y = num;	// 묶음 번호
 		}
 		
	}
	
	public static class Point{
		int x;
		int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}