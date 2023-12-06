import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        Queue<Integer> list = new PriorityQueue<>();

        for (int i : scoville) {
            list.add(i);
        }

        while (list.size() > 1 && list.peek() < K) {
            
            if (list.peek() < K) {
                list.offer(list.poll() + list.poll() * 2);
            }
            
            answer++;
        }

        if (list.peek() < K) answer = -1;
        
        return answer;
    }
}