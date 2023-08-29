class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();

        // 숫자 배열을 문자열로 만들어서 사용한다.
        for (int num : ingredient) {
            sb.append(num);
        }
        String temp = sb.toString();

        // 반복을 하면서 빵-야채-고기-빵의 문자열 1231을 문자열에서 삭제하면서 횟수를 헤아린다.
       int size = temp.length();
        while (temp.contains("1231")) {
            temp = temp.replaceAll("1231|11231231|12123131|12312311", "");
        }
        int size_temp = temp.length();
        answer += (size - size_temp) / 4;

        return answer;
    }
}