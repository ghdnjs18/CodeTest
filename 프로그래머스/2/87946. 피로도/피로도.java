class Solution {
    public int solution(int k, int[][] dungeons) {
        int answer = -1;

        // 순열 함수를 이용해 모든 경우의 수를 반복한다.
        answer = permutation(dungeons, k, new boolean[dungeons.length], 0, answer);

        return answer;
    }
    
    public int permutation(int[][] dungeons, int k, boolean[] check, int depth, int answer) {
        // 던전 탐험 처리
        for (int i = 0; i < dungeons.length; i++) {
            // 던전 탐험 가능 여부 확인
            if (!check[i] && dungeons[i][0] <= k) {
                check[i] = true; // 해당 경우의 수에서는 확인 처리
                answer = permutation(dungeons, k - dungeons[i][1], check, depth+1, answer);
                check[i] = false; // 다음 경우의 수에서는 초기화 처리
            }
        }

        return answer = Math.max(answer, depth);
    }
}