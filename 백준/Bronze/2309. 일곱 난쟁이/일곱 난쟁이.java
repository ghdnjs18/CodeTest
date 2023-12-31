import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 입력값으로 리스트 생성
        List<Integer> list = new ArrayList<>();
        // 7명의 난쟁이의 키의 합은 100이기 때문에 초과한 키는 다른 난쟁이의 키이다.
        int total = -100;
        for (int i = 0; i < 9; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            total += num;
            list.add(num);
        }
        bufferedReader.close();

        // 키를 비교하기 쉽게 하기 위해 오름차순으로 정렬한다.
        list.sort((o1, o2) -> o1 - o2);

        // 두 난쟁이의 키의 합이 100의 초과한 키가 될 경우 리스트에서 제외하고 반복문 종료.
        for (int i = 0; i < list.size() -1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) + list.get(j) == total) {
                    list.remove(j);
                    list.remove(i);
                    break;
                }
            }
            if (list.size() == 7) break;
        }

        for (int i : list) {
            System.out.println(i);
        }
    }
}