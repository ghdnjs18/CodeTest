import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 숫자의 개수 N, 삭제하는 숫자의 개수 K, N자리 숫자
    * 목표 : K개를 삭제했을 경우 가장 큰 수
    * 주어진 숫자에서 삭제한 길이의 숫자를 최소 값으로 숫자를 만든다.
    * 주어진 숫자에서 삭제 가능한 자리까지 확인을 하면서 가장 큰 수를 찾는다.
    * -> 찾은 가장 큰 수를 만드는 숫자의 앞자리에 넣고 지나온 숫자 만큼 삭제하는 숫자를 차감한다.
    * -> 해당 위치의 숫자 다음부터 반복을 한다.
    * 확인하는 반복이 많아 시간 초과
    * -> 반복을  줄일 수 있는 방법을 찾아야한다.
    * -> 숫자를 반복을 하면서 리스트에 숫자를 넣고 다음 숫자와 리스트의 뒷자리 부터 비교해서 작은 경우 삭제하고 삭제하는 개수를 차감해준다.
    * -> 삭제되는 숫자가 해당 숫자에서 가장 큰 경우와 뒤에 오는 숫자가 계속 작아질 경우 더해지기만 한다.
    * -> 마지막에 만들어야 하는 숫자 길이보다 긴 숫자들을 삭제처리 해줘야한다.
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        // 숫자의 길이
        int N = Integer.parseInt(stringTokenizer.nextToken());
        // 삭제하는 숫자의 개수
        int K = Integer.parseInt(stringTokenizer.nextToken());
        // 삭제된 숫자의 길이
        int length = N - K;

        char[] num = bufferedReader.readLine().toCharArray();
        /*char[] result = new char[length];
        Arrays.fill(result, '0');
        // 삭제하는 시작위치
        int curIdx = 0;
        // 만드는 숫자 범위 만큼 반복
        for (int i = 0; i < length; i++) {
            // 넘어간 위치와 비교하기 위해 위치 확인
            int preIdx = curIdx;
            // 넘어간 위치에서 삭제 가능한 개수 만큼 반복
            for (int j = preIdx; j <= preIdx + K; j++) {
                if (result[i] < num[j]) {
                    result[i] = num[j];
                    curIdx = j + 1;
                }
            }
            // 앞의 숫자를 삭제 시킨 만큼 삭제한 개수를 차감
            if (preIdx != curIdx && K > 0) {
                result[i] = num[curIdx - 1];
                K -= curIdx - preIdx - 1;
            }
        }*/

        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!list.isEmpty() && K > 0 && list.peekLast() < num[i]) {
                list.pollLast();
                K--;
            }
            list.add(num[i]);
        }

        while (length < list.size()) {
            list.pollLast();
        }

        StringBuilder stringBuilder = new StringBuilder();
//        for (char c : result) {
        for (char c : list) {
            stringBuilder.append(c);
        }
        System.out.println(stringBuilder);
    }
}