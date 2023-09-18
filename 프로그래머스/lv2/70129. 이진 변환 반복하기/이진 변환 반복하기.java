class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int temp = 0;

        // s의 길이가 1일 될때까지 반복
        while (s.length() != 1) {
            // 이진 변환 횟수
            answer[0] += 1;
            // 기존 길이 - 0 제거 후 길이 = 제거한 0의 개수
            temp = s.length();
            s = s.replace("0", "");
            answer[1] += temp - s.length();

            // 0 제거 후 길이를 다시 이진법으로 만든 후 s값으로 사용
            s = Integer.toBinaryString(s.length());
        }

        return answer;
    }
}