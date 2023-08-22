import java.util.Arrays;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        // score를 오름 차순 정렬한다.
        Arrays.sort(score);
        
        // for-loop로 큰 수를 뽑기 위해서 높은 인덱스부터 뽑아 계산한다.
        for (int i = score.length - m; i >= 0; i = i - m) {
            answer += score[i] * m;
        }

        return answer;
    }
}