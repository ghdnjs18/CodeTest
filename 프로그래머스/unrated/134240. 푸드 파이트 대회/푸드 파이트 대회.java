import java.util.*;

class Solution {
    public String solution(int[] food) {
        String answer = "";

        // ArrayDeque선언
        Deque<String> deque = new ArrayDeque<>();
        // 중간 지점인 물을 기준점으로 먼저 넣어준다.
        deque.add("0");

        // 큰 칼로리 부터 작은 칼로리 까지 반복하는 for-loop 생성
        for (int i = food.length-1; i > 0; i--) {
            if (food[i] / 2 > 0) { // 해당 칼로리가 짝수 개수가 되는지 확인
                // 짝수 개수만큼 양쪽에 넣어준다.
                deque.addFirst(String.valueOf(i).repeat(food[i] / 2));
                deque.addLast(String.valueOf(i).repeat(food[i] / 2));
            }
        }

        // ArrayDeque의 값들을 answer에 추가해준다.
        while (deque.iterator().hasNext()) {
            answer += deque.pop();
        }

        return answer;
    }
}