import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        List<Integer> nums = new ArrayList<>();
        int zero_cnt = 0;
        int num_cnt = 0;

        // 당첨번호를 list에 넣어준다.
        for (int num : win_nums) {
            nums.add(num);
        }

        // 내가 선택한 로또 번호를 순회한다.
        for (int lotto : lottos) {
            // 0일 경우 0을 카운트 한다.
            if (lotto == 0) zero_cnt++;
            else {
                // 해당 번호가 당첨번호에 있는지 확인하고 카운트 한다.
                if (nums.contains(lotto)) num_cnt++;
            }
        }

        // 등수는 맞은 개수의 역순
        answer[0] = zero_cnt < 1? num_cnt < 1 ? 6 : 7 - (num_cnt + zero_cnt) : 7 - (num_cnt + zero_cnt); // 가려진 수를 다 맞은걸로 침
        answer[1] = num_cnt < 1? 6 : 7 - num_cnt; // 가려진 수를 다 틀린걸로 침

        return answer;
    }
}