class Solution {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        int temp = 0;

        // n을 작은 수로 m을 큰 수로 고정
        if (n > m) {
            temp = n;
            n = m;
            m = temp;
        }
        
        if (m % n == 0) { // n이 m의 약수이면 최대공약수 이자 최소공배수
            answer[0] = n;
            answer[1] = m;
        } else {
            // 최대공약수 구하기
            for (int i = n/2; i >= 1; i--) {
                if (n % i == 0 && m % i == 0) { // n의 약수중 큰수부터 확인하여 m의 약수를 찾는다.
                    answer[0] = i;
                    break;
                }
            }
            // 최소공배수 구하기
            int cnt = 1;
            while (true) { // m의 배수 중 n의 배수 찾기
                if (m * cnt % n == 0) { 
                    answer[1] = m * cnt;
                    break;
                }
                cnt++;
            }
        }
        
        return answer;
    }
}