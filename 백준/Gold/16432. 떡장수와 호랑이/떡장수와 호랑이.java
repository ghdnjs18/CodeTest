import java.util.*;
 
public class Main {    
 
    static ArrayList<Integer>[] list;
    static boolean[][] visited;
    static int[] result;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int n = scan.nextInt();
        
        list = new ArrayList[n + 1];
        for(int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
            int m = scan.nextInt();
            for(int j = 0; j < m; j++) {
                list[i].add(scan.nextInt());
            }
        }
        
        result = new int[n + 1];
        visited = new boolean[n + 2][10]; //[n번째 날][떡의 종류]
        dfs(1, 0, n);
        System.out.println("-1");
        
    }    
    
    public static void dfs(int idx, int prev, int n) {
        if(idx == n + 1) {
            for(int i = 1; i < n + 1; i++) {
                System.out.println(result[i]);
            }
            System.exit(0);
        }
    
        for(int i = 1; i < 10; i++) {
            if(i != prev && visited[idx + 1][i] == false && list[idx].contains((Integer)i)) {
                visited[idx + 1][i] = true;
                result[idx] = i;
                dfs(idx + 1, i, n);
            }
        }
    }
}
