class Solution {
    public int solution(int[] number) {
        int answer = 0;
        
        // 3자리수를 뽑아 순차적으로 모든 경우의 수를 계산
        for (int i = 0; i < number.length; i++) {
            for (int j = i+1; j < number.length; j++) {
                for (int k = j+1; k < number.length; k++) {
                    if (number[i] + number[j] + number[k] == 0) {
                        answer++;
                    }
                }
            }
        }
        
        return answer;
    }
}