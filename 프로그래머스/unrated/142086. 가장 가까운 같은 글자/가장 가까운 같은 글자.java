import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];

        // s를 배열로 만든다.
        char[] temp = s.toCharArray();
        // 2. key value로 갖는 map만들기
        Map<Character, Integer> map = new HashMap<>();
        // s를 순회하면서
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(temp[i])) { // s의 글자가 이미 Map에 포함 되어있으면
                answer[i] = i - map.get(temp[i]); // 그리고 해당 index와 현재 Map에 저장 되어 있는 값의 차이를 answer에 대입한다.
                map.put(temp[i], i); // index로 최신화 해준다.
            } else { // 만약 포함하지 않고 처음으로 나올시
                map.put(temp[i], i); // index를 Map에 글자를 key로 해당 index를 value로 Map 삽입한다
                answer[i] = -1; // answer의 경우 처음 나온 글자니 -1을 대입한다.
            }
        }
        
        return answer;
    }
}