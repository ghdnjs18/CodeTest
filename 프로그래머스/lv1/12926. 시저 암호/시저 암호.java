class Solution {
    public String solution(String s, int n) {
        char[] answer = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            if (answer[i] != ' ') { // 공백일 경우 넘기기
                if (answer[i] < 91) { // 대문자일 경우
                    // Z를 넘어갈 경우 A로 순환
                    if (answer[i] + n > 90) { 
                        answer[i] = (char)(answer[i] + n - 26);
                    } else {
                        answer[i] = (char)(answer[i] + n);
                    }
                } else { // 소문자일 경우
                    // z를 넘어갈 경우 a로 순환
                    if (answer[i] + n > 122) {
                        answer[i] = (char)(answer[i] + n - 26);
                    } else {
                        answer[i] = (char)(answer[i] + n);
                    }
                }
            }
        }
        
        return String.valueOf(answer);
    }
}