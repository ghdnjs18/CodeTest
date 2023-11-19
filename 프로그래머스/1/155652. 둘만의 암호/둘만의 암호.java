import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();
        String[] temp_s = s.split("");
        String[] temp_skip = skip.split("");
        List<String> alphabet = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));

        // 알파벳 전체에서 스킵에 들어있는 알파벳 제거
        for (String str : temp_skip) {
            if (alphabet.contains(str)) alphabet.remove(str);
        }

        // s의 문자들을 순환한다.
        for (String c_s : temp_s) {
            int scnt = 0; // index 카운트용
            int temp = c_s.charAt(0); // 해당 문자를 아스키코드로 사용
            // skip의 제외한 문자들 순환
            while (true) {
                if (temp > 122) temp = 96;
                if (scnt == index) break;
                // 해당문자의 다음 위치가 알파벳 리스트에 있으면 카운트
                if (alphabet.contains(String.valueOf((char)(temp+1)))) {
                    scnt++;
                }
                temp++;
            }
            // 해당위치 문자 추가
            answer.append(String.valueOf((char)temp)); 
        }

        return answer.toString();
    }
}