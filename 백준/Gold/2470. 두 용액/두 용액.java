import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] answer = new int[2];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        // 입력 값을 오름차순으로 정렬한 배열로 생성
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        Arrays.sort(array);

        // 투 포인트를 이용해 가장 큰 수 와 작은 수를 합처가면서 0에 가장 가까운 수를 찾는다.
        int left = 0;
        int right = array.length - 1;
        int min = Integer.MAX_VALUE;
        while (left < right) {
            int sum = array[left] + array[right];

            // min 보다 작으면 min 갱신
            if (Math.abs(min) > Math.abs(sum)) {
                min = Math.abs(sum);
                answer[0] = array[left];
                answer[1] = array[right];
            }
            
            if (sum == 0) { // 0일 경우 바로 종료 
                break;
            } else if (sum < 0) { // 음수일 경우 0쪽으로 가기 위해서 최소값을 올림
                left++;
            } else { // 양수일 경우 0쪽으로 가기 위해 최댓값을 내림
                right--;
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}