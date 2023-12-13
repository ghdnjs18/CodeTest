import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        
        dfs(list, new LinkedList<>(), target);
        
        return answer;
    }
    
    public void dfs(List<Integer> list, LinkedList<Integer> result, int target) {
        if (list.isEmpty()) {
            int sum = 0;
            for (Integer i : result) {
                sum += i;
            }
            if (sum == target) answer++;
            return;
        }
        for (int i = 0; i < 2; i++) {
            int chose = list.get(0);

            list.remove(0);
            result.add(chose);
            dfs(list, result, target);
            result.removeLast();
            list.add(0, chose * -1);
        }
    }
}