import java.io.IOException;

public class Main {
    /*
    * 주어진 값 : N개 줄의 0~9의 숫자 3개씩의 점수
    * 목표 : 한줄당 하나씩 선택해 N번째 줄까지 합의 최대 점수와 최소 점수
    * 그냥 배열을 이용해서 구하면 실패를 할까? -> 
    * 배열의 선택 여부에 따라 범위가 계속 변경되면서 선택을 해야한다. -> 투포인트, 슬라이딩 윈도우
    * 범위가 고정되어 있고 예제를 보면 배열이 정렬이 되어 있지 않다. -> 슬라이딩 윈도우
    * 슬라이딩 윈도우 알고리즘을 이용해서 최대 점수와 최소 점수를 구할 수 있다.
    * */

    static int[][] arr;

    public static void main(String[] args) throws IOException {
        int N = readNumber();

        arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = readNumber();
            }
        }

        int[][] maxDp = new int[N][3];
        int[][] minDp = new int[N][3];
        maxDp[0][0] = minDp[0][0] = arr[0][0];
        maxDp[0][1] = minDp[0][1] = arr[0][1];
        maxDp[0][2] = minDp[0][2] = arr[0][2];
        for (int i = 1; i < N; i++) {
            // max 계산
            maxDp[i][0] = arr[i][0] + Math.max(maxDp[i-1][0], maxDp[i-1][1]);
            maxDp[i][1] = arr[i][1] + Math.max(maxDp[i-1][0], Math.max(maxDp[i-1][1], maxDp[i-1][2]));
            maxDp[i][2] = arr[i][2] + Math.max(maxDp[i-1][1], maxDp[i-1][2]);
            // min 계산
            minDp[i][0] = arr[i][0] + Math.min(minDp[i-1][0], minDp[i-1][1]);
            minDp[i][1] = arr[i][1] + Math.min(minDp[i-1][0], Math.min(minDp[i-1][1], minDp[i-1][2]));
            minDp[i][2] = arr[i][2] + Math.min(minDp[i-1][1], minDp[i-1][2]);
        }

        int max = Math.max(maxDp[N-1][0], Math.max(maxDp[N-1][1], maxDp[N-1][2]));;
        int min = Math.min(minDp[N-1][0], Math.min(minDp[N-1][1], minDp[N-1][2]));;
        System.out.println(max + " " + min);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return flag ? -cur : cur;
    }
}