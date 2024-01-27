import java.io.*;
import java.util.*;

public class Main {
    /*
    * 나무의 갯수는 N개, 나무의 시작 높이는 0
    * 높이를 증가 시킬 수 있는 조건은 나무 2개를 1, 2로 올리거나 나무 1개를 3올린다.
    * 조건은 결국 1 + 2를 올리거나 3을 올리기 때문에 나무들의 총 높이는 결국 3의 배수가 되어야 한다.
    * 높이의 합을 3으로 나눈 수와 2로 물을 뿌린 횟수가 같아야 한다.
    * 나무의 높이의 총합이 3의 배수 이면 YES 아니면 NO가 된다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int tree = Integer.parseInt(stringTokenizer.nextToken());
            sum += tree;
            // 2가 사용 되는 횟수
            cnt += tree / 2;
        }

        // 나무 높이의 합이 3의 배수인지 화인
        // 총합을 3으로 나눈 수는 2를 사용 하는 횟수는 같아야 한다.
        if (sum % 3 != 0 || cnt < sum / 3) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }
}