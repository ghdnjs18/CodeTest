class Solution {
    public String solution(String s) {
       StringBuilder sb = new StringBuilder(s);

        int cnt = 1; // 홀수 짝수 헤아릴 체크변수
        for (int i = 0; i < s.length(); i++) {
            // 공백일 경우 cnt를 다시 홀수로 초기화
            if (sb.substring(i,i+1).equals(" ")) {
                cnt = 1;
            } else {
                if (cnt % 2 != 0) { // 홀수일 경우
                    // 해당 자리 문자를 대문자로 변환
                    sb.replace(i,i+1,sb.substring(i,i+1).toUpperCase());
                    cnt = 2;
                } else { // 짝수일 경우 소문자로 변환
                    sb.replace(i,i+1,sb.substring(i,i+1).toLowerCase());
                    cnt = 1;
                }
            }
        }
        
        return sb.toString();
    }
}