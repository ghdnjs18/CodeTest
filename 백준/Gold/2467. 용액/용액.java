import java.io.*;
import java.util.*;

public class Main {
    /**
     * 주어진 값 : 용액의 수 N, N개의 용액의 값
     * 목표 : 더해서 0에 가까운 두 용액 선택
     * 가장 낮은 용액과 가장 높은 용액을 더하면서 해당용액의 수치를 비교한다.
     * 수치가 0보다 작을 경우 낮은 용액을 올리고, 높을경우 높은 용액을 낮춰 0에 가까운 용액을 찾는다.
     */
    
    static int[] solution;
    static int min = Integer.MAX_VALUE;
    static int resultA, resultB;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 용액의 수
        int N = Integer.parseInt(bufferedReader.readLine());

        // 용액 정보 입력
        solution = new int[N];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            solution[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        findSolution();

        System.out.println(resultA + " " + resultB);
    }

    private static void findSolution() {
        int left = 0;
        int right = solution.length - 1;
        while (left < right) {

            if (solution[left] + solution[right] == 0) {
                resultA = solution[left];
                resultB = solution[right];
                break;
            } else if (solution[left] + solution[right] < 0) {
                isMin(left, right);
                left++;
            } else {
                isMin(left, right);
                right--;
            }

        }
    }

    private static void isMin(int left, int right) {
        if (min > Math.abs(solution[left] + solution[right])) {
            min = Math.abs(solution[left] + solution[right]);
            resultA = solution[left];
            resultB = solution[right];
        }
    }
}