import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        // 오름차순으로 배열 정렬
        Arrays.sort(citations);

        // h는 n보다 클 수 없기 때문에 배열의 크기만큼 반복한다.
        for (int i = 0; i <= citations.length; i++) {
            int H = 0;
            
            for (int j = 0; j < citations.length; j++) {
                // h번 이상 인용된 논문이 몇개 인지 확인
                if (i <= citations[j]) {
                    H++;
                }
            }
            
            // h편 이상이고 나머지가 h번 이하 인용됐는지 확인
            if (i <= H && i >= citations.length - H) {
                answer = i;
            } else {
                break;
            }
        }
        
        return answer;
    }
}