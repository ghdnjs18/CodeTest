class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder(number);
        StringBuilder answer = new StringBuilder();

        for (int i = 0, index = -1; i < sb.length() - k; i++) {
            char max = 0;
            for (int j = index + 1; j <= k + i; j++) {
                if (max < sb.charAt(j)) {
                    index = j;
                    max = sb.charAt(j);
                }
            }
            answer.append(max);
        }

        return answer.toString();
    }
}