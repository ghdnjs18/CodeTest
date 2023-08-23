import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        // 학생과 체육복의 개수를 key-value로 사용
        Map<Integer, Integer> students = new HashMap<>();

        // 모든 학생이 체육복을 가져있는 것으로 먼저 생성을 한다.
        for (int i = 1; i <= n; i++) {
            students.put(i, 1);
        }

        // 도난당한 체육복을 빼준다.
        for (int i = 0; i < lost.length; i++) {
            students.replace(lost[i], students.get(lost[i]) - 1);
        }

        // 여벌 체육복을 가져온 학생의 체육복 수를 늘려준다.
        for (int i = 0; i < reserve.length; i++) {
            students.replace(reserve[i], students.get(reserve[i]) + 1);
        }

        // 체육복이 없는 학생들이 옆사람 체육복을 빌린다.
        for (int i = 1; i <= n; i++) {
            // 앞에 학생이 체육복이 있는지 확인
            if (students.get(i) == 0 && students.containsKey(i - 1) && students.get(i - 1) == 2) {
                students.replace(i - 1, students.get(i - 1) - 1);
                students.replace(i, students.get(i) + 1);
                // 뒤에 학생이 체육복이 있는지 확인
            } else if (students.get(i) == 0 && students.containsKey(i + 1) && students.get(i + 1) == 2) {
                students.replace(i + 1, students.get(i + 1) - 1);
                students.replace(i, students.get(i) + 1);
            }
        }

        // 체육복을 가지고 있는 학생의 수를 헤아린다.
        for (int i = 1; i <= n; i++) {
            if (students.get(i) > 0) answer++;
        }

        return answer;
    }
}