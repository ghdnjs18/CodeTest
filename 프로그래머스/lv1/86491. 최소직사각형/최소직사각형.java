import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int width = 0;
        int hight = 0;

        for (int i = 0; i < sizes.length; i++) {
            // 2차배열안의 숫자들을 정렬한다.
            Arrays.sort(sizes[i]);
            if (width < sizes[i][0]) { // w의 가장 큰 수를 구한다.
                width = sizes[i][0];
            }
            if (hight < sizes[i][1]) { // h의 가장 큰 수를 구한다.
                hight = sizes[i][1];
            }
        }
        
        return width * hight;
    }
}