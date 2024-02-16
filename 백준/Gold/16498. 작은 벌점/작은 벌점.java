import java.io.*;
import java.util.*;

public class Main {

    /*
    * a, b, c 각각의 카드를 배열에 넣어주고 정렬을 해준다.
    * 3가지의 카드를 동시에 비교를 할 수 없기 때문에 A를 기준으로 반복하면서 가장 가까운 B를 뽑아준다.
    * A와 B 각각 가장 가까운 C를 뽑아주고 각각 벌점을 계산을 해 가장 작은 벌점을 출력한다.
    * 카드를 뽑을 때는 이분 탐색을 사용한다, 목표로는 기준이 되는 숫자 카드, 반복은 다른 카드 뭉치사이에서 비교한다.
    * 가장 가까운 값을 구하기 위해 카드의 차이를 비교해서 최소값인 카드를 뽑아준다.
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int playerCountA = Integer.parseInt(stringTokenizer.nextToken());
        int playerCountB = Integer.parseInt(stringTokenizer.nextToken());
        int playerCountC = Integer.parseInt(stringTokenizer.nextToken());

        int[] playerCardA = new int[playerCountA];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < playerCountA; i++) {
            playerCardA[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int[] playerCardB = new int[playerCountB];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < playerCountB; i++) {
            playerCardB[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(playerCardB);

        int[] playerCardC = new int[playerCountC];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < playerCountC; i++) {
            playerCardC[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(playerCardC);
        bufferedReader.close();

        int answer = Integer.MAX_VALUE;
        // A카드를 반복하면서 A카드를 기준으로 가까운 B와 C 카드를 구한다.
        for (int i = 0; i < playerCountA; i++) {
            int cardA = playerCardA[i];

            int cardB = binarySearch(cardA, playerCardB);
            // A카드에 가까운 C 카드를 구한다.
            int cardAC = binarySearch(cardA, playerCardC);
            // B카드에 가까운 C 카드를 구한다.
            int cardBC = binarySearch(cardB, playerCardC);

            // A와 가까운 수가 벌점이 작은지, B와 가까운 수가 벌점이 작은시 계산한다.
            int max = Math.max(cardA, Math.max(cardB, cardAC));
            int min = Math.min(cardA, Math.min(cardB, cardAC));
            answer = Math.min(answer, Math.abs(max - min));

            max = Math.max(cardA, Math.max(cardB, cardBC));
            min = Math.min(cardA, Math.min(cardB, cardBC));
            answer = Math.min(answer, Math.abs(max - min));

            if (answer == 0) break;
        }

        System.out.println(answer);
    }

    public static int binarySearch(int target, int[] cards) {
        
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = cards.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (cards[mid] < target) {
                left = mid + 1;
            } else if (cards[mid] > target) {
                right = mid - 1;
            } else {
                return target;
            }
            
            // 가장 가까운 값 구하기
            if (Math.abs(cards[mid] - target) < Math.abs(min - target)) {
                min = cards[mid];
            }
        }
        return min;
    }
}