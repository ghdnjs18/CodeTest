import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Integer> msg = new LinkedHashMap<>();
        Map<String, Integer> rpt = new HashMap<>();
        Set<String> check = new HashSet<>();

        // 메시지 전송 수를 측정할 map생성
        for (String id : id_list) {
            msg.put(id, 0);
        }

        // 신고자 중복 제거
        for (String str : report) {
            check.add(str);
        }

        // 신고 당한 수 기록
        for (String str : check) {
            String[] id = str.split(" ");
            rpt.put(id[1], rpt.getOrDefault(id[1], 0) + 1);
        }

        // 신고 당한 수가 제한 수를 넘으면 해당 신고자에 메시지 수 증가
        for (String str : check) {
            String[] id = str.split(" ");
            if (rpt.get(id[1]) >= k) {
                msg.put(id[0], msg.get(id[0]) + 1);
            }
        }

        // 신고 수 배열에 입력
        int i = 0;
        for (Integer value : msg.values()) {
            answer[i++] = value.intValue();
        }
        
        return answer;
    }
}