import java.util.HashMap;


class Solution {
    public String solution(String[] survey, int[] choices) {
        StringBuilder answer = new StringBuilder();
        HashMap<String, Integer> category = new HashMap<>();

        // survey와 choices의 인덱스를 같이 사용하기 위해서 for-loop사용
        for (int i = 0; i < survey.length; i++) {
            // 문자열 배열에 유형을 나누어 사용
            String[] str = survey[i].split("");
            
            if (choices[i] < 4) { // 비동의 인경우 앞의 유형에 점수 올림
                category.put(str[0], category.getOrDefault(str[0], 0) + 4 - choices[i]);
                category.put(str[1], category.getOrDefault(str[1], 0));
            } else if (choices[i] > 4) { // 동의 인경우 앞의 유형에 점수 올림
                category.put(str[1], category.getOrDefault(str[1], 0) + choices[i] - 4);
                category.put(str[0], category.getOrDefault(str[0], 0));
            }
        }
        
        // 해당 유형이 없으면 사전순으로 빠른 유형 출력
        // 유형이 있을 경우 큰 값의 key값 출력
        answer.append(!category.containsKey("R") ? "R" : Math.max(category.get("R"), category.get("T")) == category.get("R") ? "R" : "T");
        answer.append(!category.containsKey("C") ? "C" : Math.max(category.get("C"), category.get("F")) == category.get("C") ? "C" : "F");
        answer.append(!category.containsKey("J") ? "J" : Math.max(category.get("J"), category.get("M")) == category.get("J") ? "J" : "M");
        answer.append(!category.containsKey("A") ? "A" : Math.max(category.get("A"), category.get("N")) == category.get("A") ? "A" : "N");

        return answer.toString();
    }
}