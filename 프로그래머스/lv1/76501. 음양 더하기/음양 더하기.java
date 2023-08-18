class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        
        for (int i = 0; i < signs.length; i++) {
            if (signs[i] == true) { // signs가 true면 더하고
                answer += absolutes[i];
            } else { // signs가 false면 뺀다.
                answer -= absolutes[i];
            }
        }
        
        return answer;
    }
}