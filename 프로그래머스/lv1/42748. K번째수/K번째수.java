import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        // answer의 인덱스를 헤아릴 cnt 선언
        int cnt = 0;
        // commands안의 배열들을 사용하기 위해 for-each문 이용
        for(int[] num : commands) {
            int i = num[0];
            int j = num[1];
            int k = num[2];

            // 필요값을 추가하면서 정렬을 하기 위해서 list 사용
            List<Integer> list = new ArrayList<>();
            // array의 i~j까지 숫자 list에 넣고 정렬
            IntStream.rangeClosed(i-1, j-1).forEach(x -> {list.add(array[x]);});
            Collections.sort(list);
            // k번째의 인덱스의 list값 answer에 넣기
            answer[cnt++] = list.get(k-1);
        }
        
        return answer;
    }
}