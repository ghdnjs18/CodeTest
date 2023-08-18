class Solution {
    public int solution(int n) {
        int answer = 0;
        
        // 가장 작은 n의 수 3보다 작은 2부터 시작해서 n까지 반복
        for (int i = 2; i < n; i++) {
            if (n % i == 1) { // 나머지가 1일경우
                answer = i; // 자연수 i를 정답으로 넣는다.
                break; // 정답이 나왔으니 for문을 종료
            }
        }
        
        return answer;
    }
}