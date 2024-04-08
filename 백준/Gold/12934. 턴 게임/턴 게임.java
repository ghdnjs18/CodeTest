import java.io.*;

public class Main {
    /*
    * 주어진 값 : 윤호 점수 X, 동혁 점수 Y
    * 목표 : X, Y 가 i번 더해서 가능한 X의 최소 횟수
    * 1부터 i번까지 진행했을 경우에 가능한 점수는 i * (i + 1) / 2 점으로 X + Y 가 해당 점수를 넘으면 불가능하다.
    * 게임을 진행하는 횟수를 구한 뒤 게임을 역순으로 진행을 하면서 큰 수를 이길 수 있는 윤호의 점수를 확인해 이기는 횟수를 헤아린다.
    * */

    public static void main(String[] args) throws IOException {
        // 윤호 점수
        long X = readNumber();
        // 동혁 점수
        long Y = readNumber();

        // 게임 진행 횟수 구하기
        int round = 1;
        int cnt = -1;
        while (true) {
            if ((long) round * (round + 1) / 2 == X + Y) {
                break;
            } else if ((long) round * (round + 1) / 2 > X + Y) {
                round = -1;
                break;
            }
            round++;
        }

        // 게임이 가능한 경우
        if (round != -1) {
            cnt = 0;
            // 큰수를 빼가면서 이긴 횟수를 구한다.
            for (int i = round; i > 0; i--) {
                if (X >= i) {
                    X -= i;
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    private static long readNumber() throws IOException {
        long cur = System.in.read() & 15;
        long next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        return flag ? -cur : cur;
    }
}