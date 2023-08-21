import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        // 중복 숫자를 자동으로 정리하기 위해 set을 사용.
        Set<Integer> set = new TreeSet<>();
        // 이중for문을 이용하여 배열의 숫자 2개의 합을 set에 넣어준다.
        for (int i = 0; i < numbers.length-1; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }
        
        // set의 크기와 같은 int[] answer 선언
        int[] answer = new int[set.size()];
        int cnt = 0;
        // set의 값을 answer에 넣어준다.
        for (int num : set) {
            answer[cnt++] = num;
        }
        
        return answer;
    }
}