import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int[] answer; // 조건에 따라 크기를 변경해야되서 선언만한다.
        
        if (arr.length == 1) { // 배열의 길이가 1이면 -1을 넣어준다.
            answer = new int[]{-1};
        } else { // answer배열을 arr보다 한자리 작은 크기로 초기화한다.
            answer = new int[arr.length - 1];
            // answer의 인덱스 i 와 arr 인덱스 j로 반복해준다.
            for (int i = 0, j = 0; i < answer.length; i++, j++) {
                // arr의 최솟값인 경우 j 인덱스를 하나 증가시켜준다.
                if (arr[j] == Arrays.stream(arr).min().getAsInt()) j++;
                answer[i] = arr[j];
            }
        }
        
        return answer;
    }
}