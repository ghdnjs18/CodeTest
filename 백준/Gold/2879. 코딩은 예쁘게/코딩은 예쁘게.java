import java.io.*;
import java.util.*;

public class Main {
    /*
     * 주어진 값 : 줄의 개수 N, 현재 줄에 있는 탭의 개수 N개, 목표 탭의 개수 N개(0 <= 탭의 개수 <= 80)
     * 조건
     * 1. 연속된 줄을 그룹으로 선택하고 -> 붙어 있는 인덱스를 확인 해야한다.
     * 2. 선택된 줄의 앞에 탭 1개를 추가하거나 삭제한다. -> 1씩 이동 가능
     * 3. 탭을 삭제하기 위해서는 모든 선택줄에 탭이 있어야한다. -> -로는 갈 수 없다.
     * 목표 : 편집 횟수의 최솟값
     * 현재 탭수에 목표 탭수를 빼 목표 탭수를 만드는 데 필요한 텝의 수를 알 수 있다.
     * 필요한 탭의 수가 음수, 양수중 교차해서 나올 경우, 같이 선택해서 변경 시 횟수가 증가해 끊어서 계산해야한다.
     * -> 다음 위치의 탭 수의 차이가 앞의 자리가 큰만큼 위치를 이동할 수 있다.
     * -> 첫 번째와 마지막 번째를 비교하기 위해 추가로 가상의 위치에 0을 넣어준다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 줄의 개수
        int N = Integer.parseInt(bufferedReader.readLine());

        // 탭 정보 입력
        int[] tap = new int[N];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            tap[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        // 현재 탭수에 목표 탭수를 빼 0을 만드는 데 필요한 텝의 수를 알 수 있다.
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            tap[i] -= Integer.parseInt(stringTokenizer.nextToken());
        }
        
        int cnt = 0;
        for (int i = 0; i < N - 1; i++) {
            // 동일한 부호일 경우
            if (tap[i] * tap[i + 1] > 0) {
                cnt += Math.max(0, Math.abs(tap[i]) - Math.abs(tap[i + 1]));
            // 다른 부호 혹은 0일 경우
            } else {
                cnt += Math.abs(tap[i]);
            }
        }

        System.out.println(cnt + Math.abs(tap[N - 1]));
    }
}