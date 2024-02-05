import java.io.*;
import java.util.*;

public class Main {
    /*
    * 피타고라스 정리를 이용해서 직삼각형의 여부를 알 수 있다.
    * a^2 + b^2 = c^2 가 성립해야 한다.
    * a^2 + b^2 + c^2 = c^2 + c^2 -> a^2 + b^2 + c^2 = 2 * c^2 가 된다.
    * 좌표를 이용해 삼각형의 변의 길이를 구하고[(x2 - x1)^2 + (y2 - y1)^2] 변의 길이를 비교해 직삼각형 여부를 판단한다.
    * 
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        // 입력값을 x, y 좌표로 넣어준다.
        long[][] coor = new long[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            coor[i][0] = Integer.parseInt(stringTokenizer.nextToken());
            coor[i][1] = Integer.parseInt(stringTokenizer.nextToken());
        }
        bufferedReader.close();

        // 피타고리스 정리를 이용해서 삼각형들이 직삼각형이 되지는 확인한다.
        int cnt = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                for (int k = j + 1; k < N; k++) {
                    long dist1 = (coor[j][0] - coor[i][0]) * (coor[j][0] - coor[i][0]) + (coor[j][1] - coor[i][1]) * (coor[j][1] - coor[i][1]);
                    long dist2 = (coor[k][0] - coor[j][0]) * (coor[k][0] - coor[j][0]) + (coor[k][1] - coor[j][1]) * (coor[k][1] - coor[j][1]);
                    long dist3 = (coor[i][0] - coor[k][0]) * (coor[i][0] - coor[k][0]) + (coor[i][1] - coor[k][1]) * (coor[i][1] - coor[k][1]);

                    if (dist1 + dist2 + dist3 == 2 * Math.max(dist1, Math.max(dist2, dist3))) {
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}