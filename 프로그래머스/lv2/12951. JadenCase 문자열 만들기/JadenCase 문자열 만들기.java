class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        // 전체를 소문자로 변경하여 배열로 만듬
        String[] str = s.toLowerCase().split(" ");

        // 끊어진 문자열을 순환하면서 앞지리를 대문자로 만들고 나머지 문자와 공백을 넣어줌
        for (String string : str) {
            if (string.equals("")) answer.append(" ");
            if (!string.equals("")) answer.append(string.substring(0, 1).toUpperCase() + string.substring(1, string.length()) + " ");
        }

        // 마지막 문자열에서 공백이 들어가므로 마지막 공백은 삭제
        if (answer.length() > s.length()) answer.deleteCharAt(answer.lastIndexOf(" "));
        // 문자열 끝나고 공백일 경우 공백 추가
        if (s.length() > answer.length()) answer.append(" ".repeat(s.length() - answer.length()));
        return answer.toString();
    }
}