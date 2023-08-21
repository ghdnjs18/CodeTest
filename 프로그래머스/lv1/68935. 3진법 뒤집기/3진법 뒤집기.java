class Solution {
    public int solution(int n) {
        // 10진수의 수를 3진수로 변환하여 StringBuilder에 넣는다.
        StringBuilder sb = new StringBuilder(Integer.toUnsignedString(n, 3));
        // sb에 문자열을 역순으로 돌려 3진수를 10진수로 변환한다.
        int answer = Integer.parseInt(sb.reverse().toString(), 3);
        
        return answer;
    }
}