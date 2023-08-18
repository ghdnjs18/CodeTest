class Solution {
    public String solution(String s) {
        String answer = "";
        
        if (s.length() % 2 == 0) { // 짝수 일경우
            char[] c = s.toCharArray(); // 문자열을 문자배열로 
            // 두글자를 반환하기 위해서 중간앞 문자와 중간문자를 가져온다.
            answer += String.valueOf(c[s.length()/2-1]);
            answer += String.valueOf(c[s.length()/2]);
        } else { // 홀수 일경우
            char[] c = s.toCharArray();
            // 중간 문자를 가져온다.
            answer = String.valueOf(c[s.length()/2]);
        }
        
        return answer;
    }
}