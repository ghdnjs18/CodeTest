import java.io.IOException;

public class Main {

    /*
    * 주어진 값 : 박스의 가로, 세로, 높이, 큐브의 종류의 수 N, 큐브의 종류별 개수
    * 목표 : 박스를 채우는데 필요한 큐브의 최소 개수
    * 큐브의 크기가 큰 것부터 박스를 채우면서 사용하는 큐브의 개수를 헤아린다.
    * 큐브의 크기가 2의 제곱꼴로 큰 큐브로 채웠으면 그 다음 큰 큐브를 개수를 헤아릴떄는
    * 이전 큐브를 넣은 개수의 8을 곱하면 채워진 공간의 크기를 알 수 있다.
    * */

    static long boxsize = 1;
    static int[] box = new int[3], cube;

    public static void main(String[] args) throws IOException {

        // 박스 크기 입력
        for (int i = 0; i < 3; i++) {
            box[i] = readNumber();
            boxsize *= box[i];
        }

        // 큐브의 종류의 개수
        int N = readNumber();

        // 큐브 정보 입력
        cube = new int[N];
        for (int i = 0; i < N; i++) {
            cube[readNumber()] = readNumber();
        }

        System.out.println(solve(N - 1, 0, 0));
    }

    private static long solve(int blockSize, long freCubeCnt, long cnt) {
        // 박스에 현재 큐브의 크기가 들어갈 수 있는 개수
        long totalCubeCnt = 1;
        for (int i = 0; i < 3; i++) {
            totalCubeCnt *= box[i] >> blockSize;
        }

        // 현재 큐브 크기의 주어진 개수
        int curCubeCnt = cube[blockSize];
        // 이전 큐브들의 크기로 채워진 부피만큼 개수 제거
        totalCubeCnt -= freCubeCnt;
        // 채울수 있는 부피만큼 박스 채우기
        long fillCubeCnt = Math.min(curCubeCnt, totalCubeCnt);

        if (blockSize == 0) {
            if (freCubeCnt + fillCubeCnt != boxsize) {
                return -1;
            }
            return cnt + fillCubeCnt;
        }

        return solve(blockSize - 1, (freCubeCnt + fillCubeCnt) << 3, cnt + fillCubeCnt);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        return flag ? -cur : cur;
    }
}