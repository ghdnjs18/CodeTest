import java.io.*;

public class Main {
    /*
    * 문자열 S를 문자열 T로 만들 수 있는지 여부를 확인해 가능하면 1, 불가능하면 0 출력
    * 조건은 문자열 뒤에 A 추가 하거나 문자열을 뒤집고 뒤에 B를 추가하는 2가지 조건이 있다.
    * 문자열 S에서 추가를 하면서 접근을 하기에는 A를 추가할지 B를 추가할지 분기점이 생긴다.
    * 하지만, 문자열 T에서 삭제를 하는 식으로 간다면 뒤에 있는 문자에 따라 조건이 선택돼 분기점이 없다.
    * 조건에 맞기 문자열 T를 S의 길이까지 삭제하면 만들 수 있는지 여부를 알 수 있다.
    * */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String S = bufferedReader.readLine();
        StringBuilder T = new StringBuilder(bufferedReader.readLine());
        
        while (S.length() != T.length()) {
            // 마지막이 A면 A삭제
            if (T.charAt(T.length()-1) == 'A') {
                T.deleteCharAt(T.length()-1);
            // 마지막이 B면 B삭제 후 문자열 뒤집기
            } else {
                T.deleteCharAt(T.length()-1);
                T.reverse();
            }
        }

        // S와 T가 같으면 1 아니면 0
        if (S.contentEquals(T)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}