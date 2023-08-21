class Solution {
    public int solution(String s) {
        StringBuilder answer = new StringBuilder();

        // 문자열 s를 순회
        for (int i = 0; i < s.length(); i++) {
            // 문자 하나를 확인하여 정규식을 통해 숫자인지 체크 
            if (s.substring(i, i+1).matches("\\d")) {
                answer.append(s, i, i+1); // 숫자일시 answer에 추가
            } else { // 문자일 경우
                // 문자열 2자리 까지 확인하여 switch-case사용하여 answer추가
                // 해당 문자의 남은 글자 수 만큼 인덱스 추가
                switch (s.substring(i, i+2)) { 
                    case "ze" : answer.append("0"); i = i + 3; break;
                    case "on" : answer.append("1"); i = i + 2; break;
                    case "tw" : answer.append("2"); i = i + 2; break;
                    case "th" : answer.append("3"); i = i + 4; break;
                    case "fo" : answer.append("4"); i = i + 3; break;
                    case "fi" : answer.append("5"); i = i + 3; break;
                    case "si" : answer.append("6"); i = i + 2; break;
                    case "se" : answer.append("7"); i = i + 4; break;
                    case "ei" : answer.append("8"); i = i + 4; break;
                    case "ni" : answer.append("9"); i = i + 3; break;
                }
            }
        }

        return Integer.parseInt(answer.toString());
    }
}