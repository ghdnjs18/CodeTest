import java.io.*;
import java.util.*;

public class Main {
    /*
    * 입력값과 횟수를 담은 큐를 이용해 너비 탐색을 한다.
    * 숫자의 이동 방법은 3가지
    * 1. 3으로 나눠지면 3으로 나누고 횟수를 증가시킨다.
    * 2. 2로 나눠지면 2로 나누고 횟수를 증가시킨다.
    * 3. 1을 뺀고 횟수를 증가시킨다.
    * 1이 되면 횟수를 출력해준다.
    * 주어진 숫자가 10^18로 int의 범위를 넘어가서 boolean 타입을 사용할 수 없어 숫자의 사용 여부를 set을 이용해서 확인해준다.
    * */

    public static void main(String[] args) throws IOException {
        long N = readNumber();

        long cnt = bfs(N);

        System.out.println(cnt);
    }
    
    private static long readNumber() throws IOException {
        long cur = System.in.read() & 15;
        long next = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        if (next == 13) System.in.read();
        return cur;
    }

    private static long bfs(long N) {
        Queue<long[]> queue = new LinkedList<>();
        queue.offer(new long[]{N, 0});

        Set<Long> set = new HashSet<>();

        while (!queue.isEmpty()) {
            long[] cur = queue.poll();
            long num = cur[0];
            long cnt = cur[1];

            if (num == 1) {
                return cnt;
            }

            long next = num / 3;
            if (num % 3 == 0 && !set.contains(next)) {
                queue.offer(new long[]{next, cnt + 1});
                set.add(next);
            }
            next = num / 2;
            if (num % 2 == 0 && !set.contains(next)) {
                queue.offer(new long[]{next, cnt + 1});
                set.add(next);
            }
            next = num - 1;
            if (!set.contains(next)) {
                queue.offer(new long[]{next, cnt + 1});
                set.add(next);
            }
        }

        return 0;
    }
}