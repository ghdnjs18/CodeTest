import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        int total = Integer.parseInt(bufferedReader.readLine());

        // 주어진 개수만큼의 배열 선언
        String[] deque = new String[total];
        int start = 0;
        int end = 0;
        for (int i = 0; i < total; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            String command = stringTokenizer.nextToken();
            switch (command) {
                case "push_front":
                    // 시작 위치와 마지막 위치가 다르면 값이 있다.
                    if (start != end) {
                        // 마지막 위치부터 시작 위치까지 하나씩 뒤로 옮긴 후, 시작 위치에 입력값을 넣는다.
                        for (int j = end - 1; j >= start; j--) {
                            deque[j + 1] = deque[j];
                        }
                        deque[start] = stringTokenizer.nextToken();
                        // 입력값이 하나 늘었기 때문에 마지막 위치도 올린다.
                        end++;
                    } else { // 입력값을 넣고 값의 마지막 위치 인덱스를 올린다.
                        deque[end++] = stringTokenizer.nextToken();
                    }
                    break;
                case "push_back":
                    // 입력값을 넣고 값의 마지막 위치 인덱스를 올린다.
                    deque[end++] = stringTokenizer.nextToken();
                    break;
                case "pop_front":
                    // 입력값이 있으면 시작 위치의 값을 출력하고 시작 위치값을 올린다.
                    if (start != end) {
                        stringBuilder.append(deque[start++]).append("\n");
                    } else {
                        // 입력 값이 없으면 -1 출력
                        stringBuilder.append("-1\n");
                    }
                    break;
                case "pop_back":
                    // 입력값이 있으면 마지막 위치 -1의 값을 출력하고 마지막 위치값을 내린다.
                    if (start != end) {
                        stringBuilder.append(deque[end - 1]).append("\n");
                        end--;
                    } else {
                        stringBuilder.append("-1\n");
                    }
                    break;
                case "size":
                    // 마지막 위치와 시작 위치의 차이로 입력값의 갯수를 출력한다.
                    stringBuilder.append(end - start).append("\n");
                    break;
                case "empty":
                    if (start != end) {
                        stringBuilder.append("0\n");
                    } else {
                        stringBuilder.append("1\n");
                    }
                    break;
                case "front":
                    // 입력값이 있으면 시작 위치의 값을 출력한다.
                    if (start != end) {
                        stringBuilder.append(deque[start]).append("\n");
                    } else {
                        stringBuilder.append("-1\n");
                    }
                    break;
                case "back":
                    // 입력값이 있으면 마지막 위치 -1의 값을 출력한다.
                    if (start != end) {
                        stringBuilder.append(deque[end-1]).append("\n");
                    } else {
                        stringBuilder.append("-1\n");
                    }
                    break;
            }
        }
        bufferedReader.close();

        System.out.println(stringBuilder);
    }
}