class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int carpet = brown + yellow;

        // brown + yellow의 약수 중에 곱해서 brown + yellow가 되는 두 수
        // 해당 두 수들의 한 쌍 중에 큰 값이 brown의 반보다 작은 것 중 가장 큰 값에서 brown으로 둘레가 가능한 한 쌍
        // 둘레 (가로 + 세로 * 2 - 4 = brwon)
        for (int i = brown / 2 - 1; i > 0; i--) {
             if (carpet % i == 0) {
                 if ((carpet / i + i) * 2 - 4 == brown) {
                    answer[0] = i;
                    break;
                 }
             }
        }
        answer[1] = carpet / answer[0];

        return answer;
    }
}