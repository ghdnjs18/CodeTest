import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        
        List<Integer> list = new LinkedList<>();

        for (int i : arr) {
            if (!list.isEmpty() && i == list.get(list.size()-1)) continue;
            list.add(i);
        }

        return answer = list.stream().mapToInt(i -> i).toArray();
    }
}