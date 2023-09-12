import java.util.Arrays;

class Solution {
    public String solution(String s) {
        // 배열로 하나하나 때어낸다.
        String[] str = s.split(" ");

        // 스트림을 사용해서 max와 min을 가져온다.
        int max = Arrays.stream(str).mapToInt(i -> Integer.parseInt(i)).max().getAsInt();
        int min = Arrays.stream(str).mapToInt(i -> Integer.parseInt(i)).min().getAsInt();

        return min + " " + max;
    }
}