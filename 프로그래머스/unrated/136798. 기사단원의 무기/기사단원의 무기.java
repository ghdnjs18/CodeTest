class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;

        // 기사 수 만큼 반복
        for (int i = 1; i <= number; i++) {
            int cnt = 1; // 소인수분해 하기 위해 1로 선언
            int temp = i; // 해당 수를 소인수분해 하며 변경을 하기 위해 i 값 입력
            for (int j = 2; j <= temp; j++) { // 2부터 소인수분해
                if (temp % j == 0) {
                    int exp = 0; // 지수 체크
                    while (temp % j == 0) { // j로 소인수분해 반복
                        exp++;
                        temp /= j;
                    }
                    cnt *= (exp + 1); // j들의 (지수 + 1)을 모두 곱한값이 약수의 개수
                }
                // 약수의 개수가 제한 범위 이상일 경우 도중 나가기
                if (cnt > limit) break;
            }
            // 약수의 개수가 제한 범위 이상일 경우 고정수 아니면 약수의 개수 추가
            answer += cnt > limit ? power : cnt;
        }

        return answer;
    }
}