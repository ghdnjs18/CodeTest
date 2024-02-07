import java.io.*;
import java.util.*;

public class Main {
    /*
    * 빌딩은 선분으로 나타낸다. -> 높이를 y값으로 만든 좌표값을 이용해 문제를 해결한다.
    * 두 지붕을 잇는 선분이 A와 B를 제외한 다른 빌딩을 지나거나 접하지 않아야 한다. 의 조건은
    * 기울기가 동일하지 않고 기울기 차이로 보이는 것
    * 빌딩을 기준으로 왼쪽과 오른쪽으로 별도로 이전 빌딩과의 기울기가 큰 것들을 헤아리고 가장 많은 빌딩 출력
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        // 입력값 입력
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[][] coor = new int[N][2];
        for (int i = 0; i < N; i++) {
            coor[i][0] = i;
            coor[i][1] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int answer = 0;
        // 빌딩을 반복하면서 양쪽으로 빌딩을 확인한다.
        for (int i = 0; i < N; i++) {
            double leftSlope = Double.MIN_VALUE;
            double rightSlope = Double.MIN_VALUE;
            int cnt = 0;

            // 왼쪽으로 빌딩과 기울기를 비교하면서 보이는 빌딩을 헤아린다.
            for (int j = i - 1; j >= 0; j--) {
                // 왼쪽으로 갈 때는 x값이 오히려 작아지기 때문에 절대값으로 양수로 만들어서 기울기를 구한다.
                double leftCurrent = (coor[j][1] - coor[i][1]) * 1.0 / Math.abs(coor[j][0] - coor[i][0]);

                // 첫 번째 빌딩이거나 이전 기울기 보다 클 경우
                if (leftSlope == Double.MIN_VALUE || leftCurrent > leftSlope) {
                    leftSlope = leftCurrent;
                    cnt++;
                }
            }

            // 오른쪽으로 빌딩과 기울기를 비교하면서 보이는 빌딩을 헤아린다.
            for (int j = i + 1; j < N; j++) {
                // 기울기 구하기
                double rightCurrent = (coor[j][1] - coor[i][1]) * 1.0 / (coor[j][0] - coor[i][0]);

                // 첫 번째 빌딩이거나 이전 기울기 보다 클 경우
                if (rightSlope == Double.MIN_VALUE || rightCurrent > rightSlope) {
                    rightSlope = rightCurrent;
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}
