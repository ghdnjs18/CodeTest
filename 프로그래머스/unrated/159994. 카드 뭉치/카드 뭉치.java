import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        // "No" 가 아닐 경우 "Yes"를 출력하기 위해
        String answer = "Yes"; 
        // 카드뭉치를 컨트롤 하기 위해 ArrayList를 이용한다.
        List<String> temp_cards1 = new ArrayList<>(Arrays.asList(cards1));
        List<String> temp_cards2 = new ArrayList<>(Arrays.asList(cards2));

        // goal을 for-each를 사용하여 순회한다.
        for (String str : goal) {
            // 해당 카드뭉치에 카드가 있으면서 첫 번째 카드가 goal의 문자열과 같으면 제거
            // 아니면 "No"
            if (!temp_cards1.isEmpty() && str.equals(temp_cards1.get(0))) {
                temp_cards1.remove(0);
            } else if (!temp_cards2.isEmpty() && str.equals(temp_cards2.get(0))) {
                temp_cards2.remove(0);
            } else {
                return "No";
            }
        }
        
        return answer;
    }
}