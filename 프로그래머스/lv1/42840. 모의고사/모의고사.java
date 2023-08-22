class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        // 수포자들의 문제 찍는 패턴을 배열로 만든다.
        int[] supo1 = {1, 2, 3, 4, 5};
        int[] supo2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] supo3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        // 수포자들이 맞은 문제 개수를 헤아릴 변수 선언
        int supo1_cnt = 0;
        int supo2_cnt = 0;
        int supo3_cnt = 0;

        // for-loop로 answers 순회
        for (int i = 0; i < answers.length; i++) {
            // 수포자들의 인덱스에 맞춰 정답 비교
            if (answers[i] == supo1[i % supo1.length]) {
                supo1_cnt++;
            }
            if (answers[i] == supo2[i % supo2.length]) {
                supo2_cnt++;
            }
            if (answers[i] == supo3[i % supo3.length]) {
                supo3_cnt++;
            }
        }

        // 정답개수의 모든 경우의 수를 if 문으로 구성
        if (supo1_cnt == supo2_cnt && supo2_cnt == supo3_cnt) {
            answer = new int[]{1, 2, 3};
        } else if (supo1_cnt == supo2_cnt && supo2_cnt > supo3_cnt) {
            answer = new int[]{1, 2};
        } else if (supo2_cnt == supo3_cnt && supo2_cnt > supo1_cnt) {
            answer = new int[]{2, 3};
        } else if (supo1_cnt == supo3_cnt && supo1_cnt > supo2_cnt) {
            answer = new int[]{1, 3};
        } else if (supo1_cnt > supo2_cnt && supo1_cnt > supo3_cnt) {
            answer = new int[]{1};
        } else if (supo2_cnt > supo1_cnt && supo2_cnt > supo3_cnt) {
            answer = new int[]{2};
        } else if (supo3_cnt > supo1_cnt && supo3_cnt > supo2_cnt) {
            answer = new int[]{3};
        }

        return answer;
    }
}