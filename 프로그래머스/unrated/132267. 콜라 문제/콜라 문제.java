class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;

        // 남은 빈병을 교환 못 할 때까지 반복
        while (n > a-1) {
            answer += n / a * b; // 교환한 새 콜라병
            n = n / a * b + n % a; // 교환한 새 콜라병 + 남은 빈 병
        }

        return answer;
    }
}