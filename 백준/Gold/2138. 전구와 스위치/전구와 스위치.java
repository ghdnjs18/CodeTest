import java.io.*;
import java.util.*;

public class Main {

    /*
    * 주어진 값 : 스위치와 전구 개수 N, 현재 전구의 상태, 목표 전구의 상태
    * 목표 : 현재 전구의 상태를 목표 전구의 상태로 만드는 최소 스위치 횟수
    * 첫번째 스위치에서 부터 해당 스위치를 누를지 여부는 자신 포함 양옆의 전구가 목표 상태와 다른 경우 해당 버튼을 눌러야 한다.
    * 반복의 기준을 왼쪽 전구가 일치하지 않을 경우 순차로 진행하면서 전구를 일치 시킬 수 있다.
    * 이경우 첫 전구가 두번쨰 전구와 일치하는 경우과 일치하지않을 경우 2가지로 나눌 수 있다.
    * -> 처음 스위치를 누르고 안누르고 2가지 경우로 나눠서 뒤로 스위치를 누르면서 확인을 한다.
    * */

    static int N, answer = Integer.MAX_VALUE;;
    static char[] cur;
    static char[] target;
    static char[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 스위치와 전구의 개수
        N = Integer.parseInt(bufferedReader.readLine());

        cur = bufferedReader.readLine().toCharArray();
        target = bufferedReader.readLine().toCharArray();

        int min = Math.min(solve(true), solve(false));
        System.out.println((min == Integer.MAX_VALUE) ? -1 : min);
    }

    private static int solve(boolean flag) {
        int cnt = 0;
        temp = Arrays.copyOf(cur, cur.length);
        
        // 처음 스위치를 누르고 안누르고 2가지 경우
        if (flag) {
            temp[0] = (temp[0] == '0') ? '1' : '0';
            temp[1] = (temp[1] == '0') ? '1' : '0';
            cnt++;
        }
        
        for (int i = 1; i < N; i++) {
            if (temp[i - 1] != target[i - 1]) {
                pressSwitch(i);
                cnt++;
            }
        }

        return Arrays.equals(temp, target) ? cnt : Integer.MAX_VALUE;
    }

    private static void pressSwitch(int idx) {
        if (idx > 0) temp[idx - 1] = (temp[idx - 1] == '0') ? '1' : '0';
        temp[idx] = (temp[idx] == '0') ? '1' : '0';
        if (idx < N - 1) temp[idx + 1] = (temp[idx + 1] == '0') ? '1' : '0';
    }
}