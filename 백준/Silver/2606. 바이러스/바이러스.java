import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 컴퓨터의 연결에 사용할 리스트 배열
        int computerCount = Integer.parseInt(bufferedReader.readLine());
        ArrayList<Integer>[] computerConects = new ArrayList[computerCount + 1];
        for (int i = 0; i <= computerCount; i++) {
            computerConects[i] = new ArrayList<>();
        }

        // 바이러스가 1번 부터 시작한다고 했기 때문에 바이러스인 상태로 만든다.
        boolean[] virus = new boolean[computerCount + 1];
        virus[1] = true;

        // 컴퓨터 쌍을 입력받아 컴퓨터별 연결된 컴퓨터 표시
        int pairCount = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < pairCount; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int computerNumA = Integer.parseInt(stringTokenizer.nextToken());
            int computerNumB = Integer.parseInt(stringTokenizer.nextToken());

            computerConects[computerNumA].add(computerNumB);
            computerConects[computerNumB].add(computerNumA);
        }

        // 바이러스 전파
        answer += virusTransmission(computerConects, virus, 1);

        System.out.println(answer);
    }

    public static int virusTransmission(ArrayList<Integer>[] computerConects, boolean[] virus, int computerNum) {
        int cnt = 0;
        // 연결된 컴퓨터가 바이러스 전염이 안돼있으면 전염시키고 카운트하고 전염된 컴퓨터로 반복
        for (int computer : computerConects[computerNum]) {
            if (!virus[computer]) {
                virus[computer] = true;
                cnt += virusTransmission(computerConects, virus, computer);
                cnt++;
            }
        }
        return cnt;
    }
}