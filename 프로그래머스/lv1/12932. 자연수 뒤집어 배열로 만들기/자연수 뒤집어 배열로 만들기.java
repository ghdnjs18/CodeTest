class Solution {
    public int[] solution(long n) {
        // 자연수 n을 문자열로 변경하여 한자리씩 str배열에 넣는다.
        String[] str = String.valueOf(n).split("");
        // answer의 배열 크기를 str의 크기로 지정해준다.
        int[] answer = new int[str.length];
        
        // i는 0부터 증가, j는 str의 마지막자리의 인덱스부터 감소
        for (int i = 0, j = str.length-1; i < str.length; i++, j--) {
            // str의 끝자리를 anwer앞부터 넣는다.
            answer[i] = Integer.parseInt(str[j]);
        }
        
        return answer;
    }
}