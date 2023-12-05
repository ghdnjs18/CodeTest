import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();

        int count = 0;
        int truckWeight = 0;
        while (true) {
            if (count == truck_weights.length && queue.isEmpty()) break;
            if (count < truck_weights.length) truckWeight = truck_weights[count];

            answer++;
            if (answer > bridge_length) {
                queue.poll();
            }
            if (truck_weights.length > count && queue.stream().mapToInt(n -> n).sum() + truckWeight <= weight) {
                queue.offer(truckWeight);
                count++;
            } else if (truck_weights.length > count) {
                queue.offer(0);
            }

        }

        return answer;
    }
}