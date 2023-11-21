import java.util.Arrays;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;

        // section의 인덱스를 헤아릴 변수
        int cnt = 0;
        // n을 순회하면서 section의 최소값과 순회 
        for (int i = 1; i <= n; i++) {
            // 벽을 넘어서 색칠 하는 인덱스 오버를 방지
            if (cnt >= section.length) break;
            // 값 비교시 동일 시 m만큼 인덱스 이동
            if (section[cnt] == i) {
                i += m-1; // 기존의 위치 -1
                cnt++;
                answer++;
            } else if (section[cnt] < i) { // 한번 칠 했을때 같이 넘어간 인덱스 체크
                cnt++;
                i--; 
            }
        }

        return answer;
    }
}