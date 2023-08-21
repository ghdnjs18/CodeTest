import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        // answer 배열 문자열 배열의 크기로 초기화
        String[] answer = new String[strings.length];

        // LinkedHashMap을 이용해서 key값으로 문자열,
        // value값으로 문자로 하여 나중에 value값으로 정렬을 하기위해 선언한다.
        Map<String, Character> map = new LinkedHashMap<>();
        // 먼저 사전순으로 정렬하기 위해 문자열배열을 오름차순 정렬해준다.
        Arrays.sort(strings);
        // for-each문을 이용해 문자열배열의 값을 key값으로 넣어주고
        // value는 해당 문자열의 n인덱스 위치의 문자를 넣어준다.
        for (String word : strings) {
            map.put(word, word.charAt(n));
        }

        // list를 이용해 map을 list에 넣어 Collections.sort()를 이용해서 정렬 해준다.
        // 기존 map과 동일한 형태의 map을 선언하고 for-each를 이용해 정렬한 key-value값을 넣는다.
        List<Map.Entry<String, Character>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        LinkedHashMap<String, Character> sorted = new LinkedHashMap<>();
        for (Map.Entry<String, Character> entry : entries) {
            sorted.put(entry.getKey(), entry.getValue());
        }

        // 정렬이 끝난 LinkedHashMap의 value값을 for-each를 이용해 answer에 넣어준다.
        int cnt = 0;
        for (Map.Entry<String, Character> entry : sorted.entrySet()) {
            answer[cnt++] = entry.getKey();
        }
        
        return answer;
    }
}