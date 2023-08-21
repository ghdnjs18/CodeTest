import java.util.stream.IntStream;

class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        
        for (int i = 0; i <= t.length()-p.length(); i++) {
            // String.valueOf()를 이용해 문자열을 배열로 사용
            // substring()을 처음부터 p을 길이만큼씩 나눈 것과 p를 비교
            if (Long.parseLong(t.substring(i, i + p.length())) <= Long.parseLong(p)) {
                answer++;
            }
        }
        
        // 스트림 구현
        // answer = (int)IntStream.rangeClosed(0, t.length()-p.length()).filter(i -> Long.parseLong(t.substring(i, i + p.length())) <= Long.parseLong(p)).count();
        
        return answer;
    }
}