import java.io.*;
import java.util.*;

public class Main {
    /*
    * 시작 숫자에서 목표 숫자로 이동하는 방법은 3가지다
    * +1, -1, *2, 동일하게 더해서 반복할 수 없으므로 조건별로 진행할 수 있도록 해준다.
    * 이동한 수를 기억할 수 있도록 해당 수들은 리스트에 담아서 보낸다. -> 42% 시간 초과
    * 수정 -> queue를 하나더 만들어 해당 숫자와 이동한 숫자들을 따로 반복 시킨다. -> 46% 시간 초과
    * 배열을 이용해 노드를 타고가는 형식으로 목표숫자에서 시작숫자까지 구할 수 있다. -> 47% 메모리 초과
    * queue를 배열말고 숫자만 사용.
    * 예제 입력을 봤을 때, 목표로 가는 정답이 여러게 일경우 아무거나 출력해도 상관없다.
    * */

    static int N, K;
    static int[] cnt = new int[100001];
    static int[] parent = new int[100001];

    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        N = readNumber();
        K = readNumber();

        bfs();

        stringBuilder.append(cnt[K] - 1).append("\n");
        int index = K;
        LinkedList<Integer> list = new LinkedList<>();
        while (index != N) {
            list.push(index);
            index = parent[index];
        }
        list.push(N);

        while (!list.isEmpty()) {
            stringBuilder.append(list.pop()).append(" ");
        }
        System.out.println(stringBuilder);
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static void bfs() {
        int[] next = {1, -1, 2};

        Queue<Integer> num = new LinkedList<>();
        cnt[N] = 1;
        num.offer(N);
        while (!num.isEmpty()) {
            int currentNum = num.poll();

            if (currentNum == K) return;

            int nextNum = 0;
            for (int i = 0; i < next.length; i++) {
                if (next[i] == 2) {
                    nextNum = currentNum * next[i];
                } else {
                    nextNum = currentNum + next[i];
                }

                if (nextNum >= 0 && nextNum < 100001 && cnt[nextNum] == 0) {
                    cnt[nextNum] = cnt[currentNum] + 1;
                    num.offer(nextNum);
                    parent[nextNum] = currentNum;
                }
            }
        }
    }
}