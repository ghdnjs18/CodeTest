class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        
        // left부터 right까지 반복문      
        for (int i = left; i <= right; i++) {
            // 자기를 제외한 약수를 구하기 때문에 cnt에 처음부터 1로 초기화
            int cnt = 1;
            // 1부터 해당수의 반까지 반복하면서 약수를 카운트
            for (int j = 1; j <= i/2; j++) {
                if (i % j == 0) cnt++;
            }
            // 약수의 개수를 짝수일 때 더하고 홀수일 때 뺀다.
            if (cnt % 2 == 0) {
                answer += i;
            } else {
                answer -= i;
            }
        }
        // 약수의 개수 체크하는 스트림
        // long cnt = IntStream.rangeClosed(2, left/2).filter(i -> left % i == 0).count() + 2;
        
        return answer;
    }
}