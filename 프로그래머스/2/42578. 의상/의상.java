import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;

        // 입력값을 의상 종류 별로 구분해 수량을 헤아려준다.
        Map<String, Integer> closet = new HashMap<>();
        for (String[] clothe : clothes) {
            closet.put(clothe[1], closet.getOrDefault(clothe[1], 0) + 1);
        }

        // 해당 종류의 의상을 안 입을 수 있는 경우의 수 가 있기 때문에 +1의 해준다음 경우의 수를 계산한다.
        for (int quantity : closet.values()) {
            answer *= quantity + 1;
        }

        // 모든 의상을 안 입는 경우의 수는 제외하고 출력한다.
        return answer - 1;
    }
}