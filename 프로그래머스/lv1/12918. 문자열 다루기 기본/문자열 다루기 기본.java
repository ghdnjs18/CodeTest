class Solution {
    public boolean solution(String s) {
        boolean answer = false;
        
        // 문자열이 숫자로 변하지 않으면 오류
        try {
            // 길이를 4 혹은 6 인지 확인
            if (s.length() == 4 || s.length() == 6) {
                answer = true;
                Integer.parseInt(s);
            }
        } catch (Exception e) {
            answer = false;
        }
        
        return answer;
    }
}