import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        
        answer = dfs(list, new LinkedList<>(), target);
        
        return answer;
    }
    
    public int dfs(List<Integer> list, LinkedList<Integer> result, int target) {
        if (list.isEmpty()) {
            int sum = 0;
            for (Integer i : result) {
                sum += i;
            }
            return sum == target ? 1 : 0;
        }
        
        int answer = 0;
        for (int i = 0; i < 2; i++) {
            int chose = list.get(0);

            list.remove(0);
            result.add(chose);
            answer += dfs(list, result, target);
            result.removeLast();
            list.add(0, chose * -1);
        }
        
        return answer;
    }
}