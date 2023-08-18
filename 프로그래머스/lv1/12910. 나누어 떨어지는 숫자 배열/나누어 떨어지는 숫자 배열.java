import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        // 동적 배열을 사용하기 위해 answer를 list로 선언.
        ArrayList<Integer> answer = new ArrayList<>();
        
        // divisor로 나눠지는 값을 answer에 추가한다.
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % divisor == 0) {
                answer.add(arr[i]);
            }
        }
        
        if (answer.isEmpty()) { // answer값이 비었으면 -1을 추가하고
            answer.add(-1);
        } else { // 아니면 오름차순 정렬
            answer.sort(Comparator.naturalOrder());
        }
        
        // 스트림의 메서드를 이용해 int형 배열로 변환하여 출력
        return answer.stream().mapToInt(i -> i).toArray();
    }
}