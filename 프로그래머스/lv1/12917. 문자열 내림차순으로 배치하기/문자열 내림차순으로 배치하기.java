import java.util.*;

class Solution {
    public String solution(String s) {
        // 문자열의 크기만큼 숫자형 배열을 선언한다.
        int[] temp = new int[s.length()];

        // 문자열의 문자를 인트형 배열에 넣는다.
        for (int i = 0; i < s.length(); i++) {
            temp[i] = (int)s.toCharArray()[i];
        }
        // 배열을 오름차순 정렬 해준다.
        // 알파벳의 아스키코드는 대문자가 숫자가 작고 소문자가 숫자가 더 크다
        Arrays.sort(temp);

        // 정렬한 temp의 배열의 값을 다시 문자로 StringBuilder로 넣어준다.
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            answer.append(Character.toChars(temp[i]));
        }
        // StringBuilder를 역순으로 출력한다.
        
        return answer.reverse().toString();
    }
}