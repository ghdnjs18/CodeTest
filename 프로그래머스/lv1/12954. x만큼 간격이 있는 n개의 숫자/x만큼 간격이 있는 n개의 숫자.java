class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n]; // n개의 배열의 크기로 선언
        long cnt = 0; // x씩 증가하는 숫자를 만들기 위한 변수
        
        // n개의 숫자를 지니는 리스트를 넣기 위해 n번 반복
        for (int i = 0; i < n; i++) {
            cnt += x; // x씩 증가하는 수
            answer[i] = cnt;
        }
        return answer;
    }
}