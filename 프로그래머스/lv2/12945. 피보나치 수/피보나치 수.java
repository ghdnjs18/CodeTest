class Solution {
    public static int solution(int n) {
        int answer = 0;
        long cNum = 0;
        long nNum = 1;
        long temp = 0;

        // 피보나치 수열 더하기
        for (int i = 1; i < n; i++) {
            temp = cNum % 1234567 + nNum % 1234567;
            cNum = nNum % 1234567;
            nNum = temp % 1234567;
        }
        answer = (int) (temp % 1234567);
        return answer;
    }
}