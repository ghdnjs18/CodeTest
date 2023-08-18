class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        int temp = 0;
        
        if (a == b) {
            return a;
        } else if (a > b) {
            temp = a;
            a = b;
            b = temp;
        }
        answer = b;
        for (int i = a; i < b; i++) {
            answer += i;
        }
        return answer;
    }
}