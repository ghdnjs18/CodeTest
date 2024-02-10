import java.io.*;
import java.util.*;

public class Main {
    /*
    * 좌표로 직각삼각형을 성립을 알기 위해서는 피타고리스 정리를 이용해 좌표간의 거리로 알 수 있다.
    * 빗변을 제외한 나머지 두 변이 좌표축에 평행한 직각삼각형을 이루기 위해서는 x축과 y축 각각 성립하면된다.
    * 일반 for문 구성시 시간초과 -> 경우의 수를 줄이기 위해서 재귀함수를 이용해서 조합을 만들어 사용한다.
    * 재귀함수 사용시 메모리초과 -> 간단한 계산이나 계산없이 문제를 풀 수 있는 방법이 있을 것이다.
    * 좌표축과 평행을 이루기 위해서는 빗면의 좌표를 제외한 좌표가 동일 해야한다.
    * -> 해당 좌표점을 제외하고 축의 좌표값이 동일한 좌표가 1개씩 있으면 직삼각형이 만들어진다.
    * */
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int[][] coordinate = new int[N][2];
        HashMap<Integer, Integer> coordinateX = new HashMap<>();
        HashMap<Integer, Integer> coordinateY = new HashMap<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            coordinate[i][0] = x;
            coordinate[i][1] = y;
            // 좌표의 중복된 개수를 헤아린다.
            coordinateX.put(x, coordinateX.getOrDefault(x, -1) + 1);
            coordinateY.put(y, coordinateY.getOrDefault(y, -1) + 1);
        }
        
        long cnt = 0;
        // 좌표점들을 반복하여 중복 좌표를 이용해 평행하는 삼각형의 개수를 헤아린다.
        for (int[] coor : coordinate) {
            long x = coordinateX.get(coor[0]);
            long y = coordinateY.get(coor[1]);
            cnt += x * y;
        }

        System.out.println(cnt);
    }
}
