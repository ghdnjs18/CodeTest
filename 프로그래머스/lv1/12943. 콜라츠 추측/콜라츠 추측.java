class Solution {
    public int solution(long num) { 
        // 1-2.를 하다가 보면 int의 범위를 넘어간다. num을 long으로 변경
        int answer = 0;
        
        if (num == 1) return 0; // 1인경우 0 출력
        while (!(num == 1)) {
            if (answer > 500) return -1; // 500번 반복 이후 -1 출력
            
            if (num % 2 == 0) { // 짝수라면 2로 나눈다.
                num /= 2;
            } else { // 홀수라면 3을 곱하고 1을 더한다.
                num = num * 3 + 1;
            }
            
            answer++; // 반복횟수를 체크한다.
        }          
        return answer;
    }
}