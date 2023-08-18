class Solution {
    public int solution(int[] numbers) {
        int answer = 45; // 1~9까지의 합
        
        // numbers의 배열의 수들을 다 빼면 안나온 수의 합이된다.
        for (int i = 0; i < numbers.length; i++) {
            answer -= numbers[i];
        }
        return answer;
    }
}