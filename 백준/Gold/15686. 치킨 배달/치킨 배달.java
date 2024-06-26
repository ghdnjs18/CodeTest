import java.io.*;
import java.util.*;
 
public class Main {
    static int N, M;
    static int[][] map;
    static ArrayList<Point> person;
    static ArrayList<Point> chicken;
    static int result;
    static boolean[] open;
 
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
 
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
 
        map = new int[N][N];
        person = new ArrayList<>();
        chicken = new ArrayList<>();
 
        // 미리 집과 치킨집에 해당하는 좌표를 ArrayList에 담는다.
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
 
                if (map[i][j] == 1) {
                    person.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }
        bufferedReader.close();
 
        result = Integer.MAX_VALUE;
        open = new boolean[chicken.size()];
 
        DFS(0, 0);
        System.out.println(result);
    }
 
    public static void DFS(int start, int cnt) {
        if (cnt == M) {
            int res = 0;
 
            for (int i = 0; i < person.size(); i++) {
                int temp = Integer.MAX_VALUE;
 
                // 어떤 집과 치킨집 중 open한 치킨집의 모든 거리를 비교한다.
                for (int j = 0; j < chicken.size(); j++) {
                    if (open[j]) {
                        int distance = Math.abs(person.get(i).x - chicken.get(j).x)
                                + Math.abs(person.get(i).y - chicken.get(j).y);
 
                        temp = Math.min(temp, distance);
                    }
                }
                res += temp;
            }
            result = Math.min(result, res);
            return;
        }
 
        // 백트래킹
        for (int i = start; i < chicken.size(); i++) {
            open[i] = true;
            DFS(i + 1, cnt + 1);
            open[i] = false;
        }
    }
 
}

class Point {
    int x;
    int y;
 
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}