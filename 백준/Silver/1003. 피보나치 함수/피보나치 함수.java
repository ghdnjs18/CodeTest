import java.io.*;

// 타뷸레이션 방식
//public class Main {
//    
//    static fibonacci[] fibonacci;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        int T = Integer.parseInt(bufferedReader.readLine());
//        // 주어진 N의 최대 크기에 맞게 배열 선언
//        fibonacci = new fibonacci[41];
//        for (int i = 0; i < 41; i++) {
//            fibonacci[i] = new fibonacci(0, 0);
//        }
//
//        // 입력값을 반복
//        for (int i = 0; i < T; i++) {
//            int N = Integer.parseInt(bufferedReader.readLine());
//            fibonacci(N);
//            bufferedWriter.write(fibonacci[N].zero + " " + fibonacci[N].one + "\n");
//        }
//        bufferedReader.close();
//
//        bufferedWriter.flush();
//        bufferedWriter.close();
//    }
//
//
//    public static void fibonacci(int target) {
//        fibonacci[0].zero = 1;
//        fibonacci[1].one = 1;
//
//        for (int i = 2; i <= target; i++) {
//            fibonacci[i].zero = fibonacci[i-1].zero + fibonacci[i-2].zero;
//            fibonacci[i].one = fibonacci[i-1].one + fibonacci[i-2].one;
//        }
//    }
//}
//
//class fibonacci {
//    int zero;
//    int one;
//
//    public fibonacci(int zero, int one) {
//        this.zero = zero;
//        this.one = one;
//    }
//}

// 메모이제이션 방식

public class Main {

    static int[][] fibonacci = new int[41][2];
    static boolean[] visited = new boolean[41];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(bufferedReader.readLine());

        fibonacci[0][0] = 1;
        fibonacci[1][1] = 1;
        visited[0] = true;
        visited[1] = true;

        // 입력값을 반복
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(bufferedReader.readLine());
            fibonacci(N);
            bufferedWriter.write(fibonacci[N][0] + " " + fibonacci[N][1] + "\n");
        }
        bufferedReader.close();

        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public static void fibonacci(int target) {

        if (visited[target]) {
            return;
        }

        fibonacci(target - 1);
        fibonacci(target - 2);
        visited[target] = true;
        fibonacci[target][0] = fibonacci[target-1][0] + fibonacci[target-2][0];
        fibonacci[target][1] = fibonacci[target-1][1] + fibonacci[target-2][1];
    }
}