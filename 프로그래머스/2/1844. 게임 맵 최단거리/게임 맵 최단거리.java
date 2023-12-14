import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = -1;

        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(new int[]{0, 0, 1});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int cnt = current[2];

            if (x == maps.length-1 && y == maps[0].length-1) {
                answer = cnt;
            }

            move(queue, maps, x, y + 1, cnt);
            move(queue, maps, x + 1, y, cnt);
            move(queue, maps, x, y - 1, cnt);
            move(queue, maps, x - 1, y, cnt);
        }
        
        return answer;
    }
    
    public static Queue<int[]> move(Queue<int[]> queue, int[][] maps, int x, int y, int cnt) {

        if (x >= 0 && y >= 0 && maps.length > x && maps[0].length > y && maps[x][y] != 0) {
            maps[x][y] = 0;

            queue.offer(new int[]{x, y, cnt + 1});
            return queue;
        }
        return queue;
    }
}