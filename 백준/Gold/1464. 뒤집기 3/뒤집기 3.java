import java.io.*;

public class Main {
    /*
    * 주어진 값 : 문자열 S
    * 목표 : 사전순으로 가장 앞서는 문자열 출력
    * 조건 : 앞에서 i번째 문자열을 뒤집으면서 문자열을 변경할 수 있다.
    * 첫글자와 i번의 글자를 비교해 같거나 빠른경우 현재 문자열을 뒤집고 i번째 글자를 추가한 뒤 다시 뒤집어준다.
    * 아니면 i번째 글자만 추가하고 문자열을 반복해준다.
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        // 문자열 입력
        String str = bufferedReader.readLine();

        // 첫글자 추가
        stringBuilder.append(str.charAt(0));
        
        // 문자열 반복하면서 첫글자와 비교
        for (int i = 1; i < str.length(); i++) {
            if (stringBuilder.charAt(0) >= str.charAt(i)) {
                stringBuilder.reverse();
                stringBuilder.append(str.charAt(i));
                stringBuilder.reverse();
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }

        System.out.println(stringBuilder);
    }
}