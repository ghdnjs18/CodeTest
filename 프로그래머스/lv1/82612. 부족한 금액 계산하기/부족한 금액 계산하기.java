class Solution {
    public long solution(int price, long money, int count) {
        // 제한사항에서 money가 int형의 범위를 넘어 int를 long으로 변경
        long answer = -1;

        // for-loop를 이용해 money에 빼준다.
        for (int i = 1; i <= count; i++) {
            money -= price * i;
        }
        // 돈이 -이면 절댓값을 answer에 넣어준다. 아니면 0
        if (money < 0) {
            answer = Math.abs(money);
        } else {
            answer = 0;
        }
        
        // 스트림을 이용한 문제풀기
//         long temp = money - IntStream.rangeClosed(1, count).map(i -> price * i).sum();

//         if (temp < 0) answer = Math.abs(temp);
//         else answer = 0;
        
        return answer;
    }
}