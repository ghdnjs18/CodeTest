class Solution {
    public String solution(int n) {
        String answer = "";
        
        if (n % 2 == 0) { // 짝수일 경우
            answer += "수박".repeat(n / 2);
        } else { // 홀수일 경우
            answer += "수박".repeat(n / 2);
            answer += "수";
        }
        return answer;
    }
}