class Solution {
    public int solution(String s) {
        int answer = 0;
        StringBuilder str = new StringBuilder(s);

        // 문자열이 없을 때 까지 반복
        while (str.length() > 0) {
            // 문자열이 하나 남았을 경우 횟수를 올리고 나온다
            if (str.length() == 1) { 
                answer++;
                break;
            }
            int cnt = 1; // 문자열 확인용
            int s_cnt = 1; // 첫 글자와 같은 횟수
            int d_cnt = 0; // 첫 글자와 다른 횟수
            String temp = str.substring(0, 1); // 첫 글자
            while (true) { 
                // 뒷 글자들 확인
                if (cnt+1 > str.length()) break;
                if (temp.equals(str.substring(cnt, cnt+1))) {
                    s_cnt++;
                } else {
                    d_cnt++;
                }
                cnt++;
                // 같을 경우 나온다
                if (s_cnt == d_cnt) break;
            }
            // 해당 인덱스 만큼 잘라낸다.
            str.replace(0 ,s_cnt + d_cnt, "");
            answer++;
        }

        return answer;
    }
}