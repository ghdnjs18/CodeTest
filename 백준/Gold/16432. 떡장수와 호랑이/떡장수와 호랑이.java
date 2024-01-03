import java.io.*;
import java.util.*;
 
public class Main {    
 
    static List<Integer>[] riceCake;
    static int[] eat;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        riceCake = new List[N];
        eat = new int[N];
        visited = new boolean[N+1][10];
        for (int i = 0; i < N; i++) {
            riceCake[i] = new ArrayList<>();
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int index = Integer.parseInt(stringTokenizer.nextToken());
            for (int j = 0; j < index; j++) {
                riceCake[i].add(Integer.parseInt(stringTokenizer.nextToken()));
            }
        }

        dfs(N-1, 0);

        if (eat[0] == 0) {
            System.out.println("-1");
        } else {
            for (int i : eat) {
                System.out.println(i);
            }
        }
    }

    public static void dfs(int day, int before) {
        if (day < 0) return;

        if (eat[0] == 0) {
            for (int cake : riceCake[day]) {
                if (!visited[day+1][cake] && cake != before) {
                    visited[day+1][cake] = true;

                    dfs(day - 1, cake);

                    eat[day] = cake;
                }
                if (eat[0] != 0) break;
            }
        }
    }
}
