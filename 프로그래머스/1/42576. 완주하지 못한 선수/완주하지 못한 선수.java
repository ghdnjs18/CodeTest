import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        Map<String, Integer> map = new HashMap<>();

        for (String s : participant) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for (String s : completion) {
            map.put(s, map.get(s) - 1);
        }
        answer = map.entrySet().stream().filter(m -> m.getValue() != 0).map(m -> m.getKey()).collect(Collectors.joining());

        return answer;
    }
}