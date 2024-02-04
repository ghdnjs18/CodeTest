import java.io.*;
import java.util.*;

public class Main {
    /*
    * 사선식을 이용해서 삼각형의 형태를 알 수 있다?
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 입력값을 x, y좌로 나누어 배열에 넣어준다.
        int[][] coordinate = new int[3][2];
        for (int i = 0; i < 3; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            coordinate[i][0] = x;
            coordinate[i][1] = y;
        }

        // x좌표에서 이어진 y좌표를 곱한 값과 y좌표에서 이어진 x좌표를 곱한 값을 구한다.
        int totalX = coordinate[2][0] * coordinate[0][1];
        int totalY = coordinate[2][1] * coordinate[0][0];
        for (int i = 0; i < 2; i++) {
            totalX += coordinate[i][0] * coordinate[i + 1][1];
            totalY += coordinate[i][1] * coordinate[i + 1][0];
        }

        
        if (totalX > totalY) {
            System.out.println(1);
        } else if (totalX < totalY) {
            System.out.println(-1);
        } else {
            System.out.println(0);
        }
    }
}