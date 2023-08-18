import java.util.*;

class Solution {
    public long solution(long n) {
        // 우선순위 큐를 이용해서 큰자리수 자동정렬을 만든다.
        PriorityQueue queue = new PriorityQueue(Collections.reverseOrder());
        
        // 큐에 정수n의 값을 넣어주면 자동정렬이 된다.
        queue.addAll(List.of(String.valueOf(n).split("")));

        // 높은순으로 정렬된 큐를 StringBuilder에 순차적으로 넣어준다.
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }
        
        // StringBuilder을 정수로 변환하여 값을 출력
        return Long.parseLong(sb.toString());
    }
}