class Solution {
    public long solution(long n) {
        long answer = 0;
        
        // sqrt함수를 이용해서 제곱근을 구한다.
        // 실수 제곱근과 정수 제곱근을 비교하여 일치시 정수의 제곱근
        if (Math.sqrt(n) == (int)Math.sqrt(n)) {
            // 구한 제곱근에 1을 더하고 pow함수를 이용해 제곱 시켜준다.
            answer = (long)Math.pow(Math.sqrt(n)+1, 2);
        } else {
            answer = -1;
        }
        
        return answer;
    }
}