import java.io.*;
import java.util.*;

public class Main {
    /**
     * 주어진 값 : 플레이어의 수 N, N개의 정수
     * 조건 : 다른 정수를 나눴을 때, 나머지가 0이면 승리(+1), 다른 정수로 나눠지면 패배(-1), 둘 다 아니면 무승부
     * 모든 플레이어와 한 번씩 결투, 중볻 되는 정수는 없다.
     * 목표 : 게임 종료시 점수 출력
     * 최대 플레이어수 100000를 브루트포스 알고리즘을 사용하면 시간초과가 날 것이다.
     * 나눠지는 수는 결국 해당 수의 약수 또는 배수라고 생각할 수 있다.
     * -> 반복을 하기 위해선 약수보다는 배수를 이용하는 편이 좋아보인다.
     * 플레이어 별 카드를 입력하고 카드의 범위와 배수를 알기 위해서는 플레이어가 가진 카드중 가장 큰카드로 범위를 가진다.
     * 점수를 출력하기 위해서는 카드별 어느 플레이어가 가지고 있는지를 알아야하기 떄문에 해당 카드에 플레이어의 위치를 입력한다.
     * 플레이어의 카드를 반복을 하면서 해당 카드의 배수를 다른 플레이어가 가지고 있다면 점수를 올리고 다른 플레이러는 감소 시킨다.
     */
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 플레이어의 수
        int N = Integer.parseInt(bufferedReader.readLine());

        // 카드 정보 입력
        int[] player = new int[N];
        int maxCard = 0;
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            player[i] = Integer.parseInt(stringTokenizer.nextToken());
            maxCard = Math.max(maxCard, player[i]) + 1;
        }

        // 카드별 플레이어 위치 입력
        int[] card = new int[maxCard];
        for (int i = 0; i < N; i++) {
            card[player[i]] = i + 1;
        }

        // 플레이어가 가진 카드의 배수가 되는 플레이어가 있으면 점수를 측정해준다.
        int[] score = new int[N];
        for (int num : player) {
            for (int j = num * 2; j < maxCard; j += num) {
                if (card[j] != 0) {
                    score[card[num] - 1]++;
                    score[card[j] - 1]--;
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i : score) {
            stringBuilder.append(i).append(" ");
        }

        System.out.println(stringBuilder);
    }
}