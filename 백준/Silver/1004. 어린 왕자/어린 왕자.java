import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bufferedReader.readLine());
		
		while (T > 0) {
			StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			int x1 = Integer.parseInt(stringTokenizer.nextToken());
			int y1 = Integer.parseInt(stringTokenizer.nextToken());
			int x2 = Integer.parseInt(stringTokenizer.nextToken());
			int y2 = Integer.parseInt(stringTokenizer.nextToken());
			
			int n = Integer.parseInt(bufferedReader.readLine());
			int answer=0;
			
			for(int i=0;i<n;i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());
				int x = Integer.parseInt(stringTokenizer.nextToken());
				int y = Integer.parseInt(stringTokenizer.nextToken());
				int r = Integer.parseInt(stringTokenizer.nextToken());
				
				boolean flag1 = false;
				boolean flag2 = false;
				
				if (Math.pow(x1-x, 2) + Math.pow(y1-y, 2) >Math.pow(r, 2)) {
                    flag1=true;    
                } 
				if (Math.pow(x2-x, 2) + Math.pow(y2-y, 2)>Math.pow(r, 2)) {
                    flag2=true; 
                } 
				
				if (flag1!=flag2) {
                    answer++;  
                } 
			}
			T--;
			System.out.println(answer);
		}
	}

}