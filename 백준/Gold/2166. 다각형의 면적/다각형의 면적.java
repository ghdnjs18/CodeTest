import java.io.*;
import java.util.*;

public class Main {
    /*
    * 사선식으 이용해서 좌표로 다각형의 면적을 구할 수 있다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        // 입력값을 x, y좌로 나누어 배열에 넣어준다.
        long[][] coordinate = new long[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            coordinate[i][0] = Integer.parseInt(stringTokenizer.nextToken());
            coordinate[i][1] = Integer.parseInt(stringTokenizer.nextToken());
        }

        // x좌표에서 이어진 y좌표를 곱한 값과 y좌표에서 이어진 x좌표를 곱한 값을 구한다.
        long totalX = coordinate[N - 1][0] * coordinate[0][1];
        long totalY = coordinate[N - 1][1] * coordinate[0][0];
        for (int i = 0; i < N - 1; i++) {
            totalX += coordinate[i][0] * coordinate[i + 1][1];
            totalY += coordinate[i][1] * coordinate[i + 1][0];
        }

        // x좌표로 나온 합에 y좌표로 나온 합을 빼주고 반으로 나눠준다.
        System.out.println(String.format("%.1f", Math.abs(totalX - totalY) / 2.0));
    }
}