import java.util.*;

class Solution {
    public String solution(String[] seoul) {    
        // seoul배열을 리스트화 시켜 indexof를 이용해서 출력문을 만든다.
        // return "김서방은 " + List.of(seoul).indexOf("Kim") + "에 있다";
        
        for (int i = 0; i < seoul.length; i++) {
            if (seoul[i].equals("Kim")) {
                return "김서방은 " + i + "에 있다";
            }
        }
        return "잘못된 값이 입력되는 경우는 없습니다.";
    }
}