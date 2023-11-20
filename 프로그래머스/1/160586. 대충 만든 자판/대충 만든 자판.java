class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        int cnt = 0;

        // targets을 순회한다.
        for (String target: targets) {
            // target을 문자배열로 분할한다.
            char[] temp = target.toCharArray();
            int sum = 0; // 키를 출력하는데 사용된 총 인덱스

            // target을 순회한다.
            for (int i = 0; i < temp.length; i++) {
                int key = 0; // 키를 누르는 횟수
                for (int j = 0; j < keymap.length; j++) {
                    if (keymap[j].indexOf(temp[i]) >= 0) {
                        if (key == 0) {
                            key = Math.max(keymap[j].indexOf(temp[i]) + 1, key);
                        } else {
                            key = Math.min(key, keymap[j].indexOf(temp[i]) + 1);
                        }
                    }
                }
                // keymap으로 누르지 못했으면 작성을 할 수 없다.
                if (key == 0) {
                    sum = 0;
                    break;
                } else {
                    sum += key;
                }
            }
            answer[cnt] = sum;
            // 문자열을 작성할 수 없음
            if (answer[cnt] == 0) answer[cnt] = -1;
            cnt++;
        }
        
        return answer;
    }
}