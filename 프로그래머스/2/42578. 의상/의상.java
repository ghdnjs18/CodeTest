import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> closet = new HashMap<>();
        for (String[] clothe : clothes) {
            closet.put(clothe[1], closet.getOrDefault(clothe[1], 0) + 1);
        }

        for (int quantity : closet.values()) {
            answer *= quantity + 1;
        }

        return answer - 1;
    }
}