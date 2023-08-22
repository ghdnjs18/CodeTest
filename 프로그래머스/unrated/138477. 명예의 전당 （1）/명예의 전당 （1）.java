import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        // 우선순위 큐를 이용해서 자동 정렬
        PriorityQueue<Integer> hof = new PriorityQueue<>();

        for (int i = 0; i < score.length; i++) {
            if (i < k) { // 명예의 전당 채우기
                hof.add(score[i]);
            // 명예의 전당의 가장 작은 수와 현재의 수 비교 후 현재 수가 크면 작은 수를 빼고 현재의 수 넣기
            } else if (Math.max(hof.peek(), score[i]) == score[i]) {
                hof.remove();
                hof.add(score[i]);
            }
            // 가장 작은 수는 가장 앞으로 온다.
            answer[i] = hof.peek();
        }

        return answer;
    }
}