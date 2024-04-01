import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 벽장의 수 N, 열린 문의 위치 2곳, 이동하는 순서 T개
    * 목표 : 벽장 문을 열어 사용하는 최소 문 이동수
    * 일반 브루트포스 알고리즘을 하기에는 동일한 위치의 분기점에서 알기 힘들다.
    * 반복을 할때 알아야하는 값이 현재 이동횟수, 열린문 2개로 3개의 값을 알야야 하기 때문에 3차배열로 dp를 구성해야한다.
    * 타뷸레이션 방식으로 하기에는 양쪽문이 움직이는 분기점을 확인하기에 적합하기 않아 마이그레이션 방식을 선택한다.
    * 재귀함수를 이용해 양쪽으로 이동하는 모든 경우의 수에서 최소 이동 거리를 구한다.
    * */

    static int T;
    static int[] move;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        // 벽장의 개수
        int N = readNumber();

        // 벽장에 열린 문은 2개 주어진다.
        int[] openDoor = new int[2];
        for (int i = 0; i < 2; i++) {
            openDoor[i] = readNumber();
        }

        // 벽장 사용 순서 입력
        T = readNumber();
//        int cnt = 0;
//        for (int i = 0; i < T; i++) {
//            int move = readNumber();
//
//            if (Math.abs(openDoor[0] - move) > Math.abs(openDoor[1] - move)) {
//                cnt += Math.abs(openDoor[1] - move);
//                openDoor[1] = move;
//            } else {
//                cnt += Math.abs(openDoor[0] - move);
//                openDoor[0] = move;
//            }
//        }
        // 양쪽 문의 거리에 동일한 곳을 열어야 할 경우 최소 이동횟수를 구분할 수 없다.
        // 두가지 케이스로 이동시 비교하는 방법은 그래프 탐색을 이용해서 알 수가 있다.

        // 벽장의 순서를 배열에 넣어 사용으로 수정
        move = new int[T];
        for (int i = 0; i < T; i++) {
            move[i] = readNumber();
        }

        // 최소 이동횟수 탐색
        dp = new int[T+1][N+1][N+1];
        for (int[][] ints : dp) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }
        System.out.println(findMinMove(0, openDoor[0], openDoor[1]));
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        return flag ? -cur : cur;
    }

    private static int findMinMove(int index, int firstDoor, int secondDoor) {
        if (index == T) {
            return 0;
        }

        if (dp[index][firstDoor][secondDoor] != -1) {
            return dp[index][firstDoor][secondDoor];
        }

        int firstCnt = findMinMove(index + 1, move[index], secondDoor) + Math.abs(move[index] - firstDoor);
        int secondCnt = findMinMove(index + 1, firstDoor, move[index]) + Math.abs(move[index] - secondDoor);

        dp[index][firstDoor][secondDoor] = Math.min(firstCnt, secondCnt);

        return dp[index][firstDoor][secondDoor];
    }
}