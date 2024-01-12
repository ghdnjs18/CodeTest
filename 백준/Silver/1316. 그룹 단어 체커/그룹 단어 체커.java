import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            // 알파벳 위치 확인용
            boolean[] visited = new boolean[26];
            char[] str = bufferedReader.readLine().toCharArray();
            // 첫 번째 단어 사용 표시
            visited[str[0] - 'a'] = true;
            for (int j = 1; j < str.length; j++) {
                // 앞 단어와 같으면 다음 단어로
                if (str[j] == str[j - 1]) continue;
                // 사용한 단어가 한번 더 나오면 그룹 단어가 아니다.
                if (visited[str[j] - 'a']) {
                    cnt++;
                    break;
                }
                // 연속 단어가 끝나면 사용
                visited[str[j] - 'a'] = true;
            }
        }
        System.out.println(N - cnt);
    }
}