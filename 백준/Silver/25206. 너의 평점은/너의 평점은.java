import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        float answer = 0.0f;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        float creditTotal = 0.0f;
        float gradeTotal = 0.0f;
        for (int i = 0; i < 20; i++) { // 입력이 20줄에 걸쳐 진행
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            String subject = stringTokenizer.nextToken(); // 사용 안함..
            float credit = Float.parseFloat(stringTokenizer.nextToken());
            String grade = stringTokenizer.nextToken();

            switch (grade) {
                case "A+":
                    creditTotal += credit;
                    gradeTotal += (float) (4.5 * credit);
                    break;
                case "A0":
                    creditTotal += credit;
                    gradeTotal += (float) (4.0 * credit);
                    break;
                case "B+":
                    creditTotal += credit;
                    gradeTotal += (float) (3.5 * credit);
                    break;
                case "B0":
                    creditTotal += credit;
                    gradeTotal += (float) (3.0 * credit);
                    break;
                case "C+":
                    creditTotal += credit;
                    gradeTotal += (float) (2.5 * credit);
                    break;
                case "C0":
                    creditTotal += credit;
                    gradeTotal += (float) (2.0 * credit);
                    break;
                case "D+":
                    creditTotal += credit;
                    gradeTotal += (float) (1.5 * credit);
                    break;
                case "D0":
                    creditTotal += credit;
                    gradeTotal += (float) (1.0 * credit);
                    break;
                case "F":
                    creditTotal += credit;
                    gradeTotal += (float) (0.0 * credit);
                    break;
                case "P":
                    gradeTotal += (float) (0.0 * credit);
                    break;
            }
        }

        // 0일경우 0과 0을 나누게 되면 출력이 불가
        if (creditTotal != 0.0) {
            answer = gradeTotal / creditTotal;
        }
        System.out.printf("%.6f", answer);
    }
}