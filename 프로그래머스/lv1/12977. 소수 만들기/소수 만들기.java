import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        Set<Integer> sums = new HashSet<>();

        // 서로 다른 3개를 더해주기
        for (int i = 0; i < nums.length-2; i++) {
            for (int j = i + 1; j < nums.length-1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int total = nums[i] + nums[j] + nums[k];
                    boolean chk = true;
                    // 소수의 개수 헤아리기
                    for (int l = 2; l <= total/2; l++) {
                        if (total % l == 0) {
                            chk = false;
                            break;
                        }
                    }
                    if (chk == true) answer++;
                }
            }
        }

        return answer;
    }
}