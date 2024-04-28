import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 정수 N, 조건 수행 횟수 K
    * 조건 : 정수 N에서 임의의 위치의 숫자를 바꾼다. 0은 앞으로 올 수 없다.
    * 목표 : K번 위치를 바꿨을 때, 최댓값 출력, 연산할 수 없으면 -1 출력
    * 주어진 정수가 한자리 수 이거나 두자리일 경우 0으로 끝나면 위치를 연산할 수 없다.
    * 가장 큰 자리수 부터 가장 큰 수로 위치를 변경을 해준다.
    * 최대값을 찾았을 경우, 변경횟수가 남았으면 일의 자리와 십의 자리수를 변경해준다.
    * */

    public static int[] visited = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());
        int length = String.valueOf(N).length();

        // queue에 처음 N 추가
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        while (!queue.isEmpty() && K > 0) {
            int size = queue.size();

            for (int x = 0; x < size; x++) {
                int now = queue.poll();

                // change
                for (int i = 0; i < length - 1; i++) {
                    for (int j = i + 1; j < length; j++) {
                        int next = swap(now, i, j);

                        // 앞자리가 0이거나 현재 depth에서 방문한 적이 있는 경우 queue에 add하지 않음.
                        if (next != 0 && visited[next] != K) {
                            queue.add(next);
                            visited[next] = K;
                        }
                    }
                }
            }

            K--;
        }

        if (queue.isEmpty()) {
            System.out.println("-1");
        } else {

            int result = 0;
            for (int x : queue) {
                result = Math.max(x, result);
            }

            System.out.println(result);
        }
    }

    // 교환
    public static int swap(int now, int i, int j) {

        char[] list = String.valueOf(now).toCharArray();

        char temp = list[i];
        list[i] = list[j];
        list[j] = temp;

        if(list[0]=='0') return 0;

        return Integer.parseInt(new String(list));
    }
}
