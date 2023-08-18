import java.util.*;

class Solution {
    public long solution(long n) {
        // 우선순위 큐를 이용해서 큰자리수 자동정렬을 만든다.
        PriorityQueue queue = new PriorityQueue(Collections.reverseOrder());
        String answer = "";
        
        // 큐에 정수n의 값을 넣어주면 자동정렬이 된다.
        queue.addAll(List.of(String.valueOf(n).split("")));

        // 높은순으로 정렬된 큐를 문자열에 순차적으로 넣어준다.
        while (!queue.isEmpty()) {
            answer += (String) queue.poll();
        }
        
        // 문자열을 정수로 변환하여 값을 입력
        return Long.parseLong(answer);
    }
}