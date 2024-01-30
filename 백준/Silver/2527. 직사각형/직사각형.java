import java.io.*;
import java.util.*;

public class Main {
    /*
    * 전제 조건으로 자동적으로 좌표값이 오름차순으로 들어온다.
    * 조건을 4가지로 분류 할 수 있는데,
    * 1. 두번째 직사각형의 x, y가 한번째 직사각형의 x, y들의 사이 값일때, 면이 겹치는 부분이 있다.
    * 2. 두 직사각형의 좌표값 중 하나만 일치 할 경우, 선이 겹치는 부분이 있다.
    * 3. 두 직사각형의 좌표값 쌍이 일치할 경우, 점이 겹치는 부분이 있다.
    * 4. 한 직사각형의 좌표값이 하나도 일치하지 않을 경우, 완전히 분리되어있다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int[] coordinate = new int[8];
            for (int j = 0; j < 8; j++) {
                coordinate[j] = Integer.parseInt(stringTokenizer.nextToken());
            }

            // 비교하기 위해 작은 값중 큰 수와 큰 값중 작은 수를 구한다.
            int smallX = Math.max(coordinate[0], coordinate[4]);
            int smallY = Math.max(coordinate[1], coordinate[5]);
            int bigX = Math.min(coordinate[2], coordinate[6]);
            int bigY = Math.min(coordinate[3], coordinate[7]);

            //  x, y 모두 작은 값중 큰수 보다 큰 값중 작은 수가 크게 되면 사이에 있는 형태로 면이 겹친다. 
            if (smallX < bigX && smallY < bigY) {
                stringBuilder.append("a").append("\n");
            // x나 y 중 하나만 작은 값중 큰수 보다 큰 값중 작은 수가 크게 되면 한쪽만 사이에 있는 형태로 선이 겹친다.
            } else if ((smallX < bigX && smallY == bigY) || (smallX == bigX && smallY < bigY)) {
                stringBuilder.append("b").append("\n");
            // x, y 모두 작은 값중 큰수와 큰 값중 작은 수가 같게 되면 점만 겹친다.
            } else if (smallX == bigX && smallY == bigY) {
                stringBuilder.append("c").append("\n");
            // 성립되지 않으면 겹치는 곳이 없다.
            } else {
                stringBuilder.append("d").append("\n");
            }
        }
        System.out.println(stringBuilder);
    }
}