import java.io.*;
import java.util.*;

public class Main {
    /*
    * 새로운 단어를 만드는 규칙은 2가지이다.
    * 1. 맨 앞이나 맨 뒤에 한 글자를 넣어 전의 단어가 그대로 쓰이는 경우
    * 2. 단어 중간에 한 글자가 끼워지는 경우
    * 1번의 경우는 문자를 포함하는지 확인하면 알 수 있다.
    * 2번의 경우가 복잡해 지는데, 해당 단어를 하나씩 그전 단어와 체크를 해서 다른 곳이 한 곳인 인지를 보고 확인을 할 수 있다.
    * 단어의 길이가 순차적으로 늘어나기 때문에 주어진 단어를 오름차순 정렬을 시켜 하는 것이 편해보인다.
    *
    * */
    static String[] words;
    static boolean[] visited;
    static String word = "";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int d = Integer.parseInt(stringTokenizer.nextToken());
        String keyword = stringTokenizer.nextToken();

        words = new String[d];
        visited = new boolean[d];
        for (int i = 0; i < d; i++) {
            words[i] = bufferedReader.readLine();
        }
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());

        BFS(keyword);

        System.out.println(word);
    }

    public static void BFS(String keyword) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(keyword);
        visited[0] = true;
        while (!queue.isEmpty()) {
            word = queue.poll();

            for (int i = 1; i < words.length; i++) {
                // 다음 크기의 단어인지 확인을 한다.
                if (!visited[i] && word.length() + 1 == words[i].length()) {
                    // 한 글자가 앞, 뒤에 붙었을 경우 전 단어가 그대로 포함 된다.
                    if (words[i].contains(word)) {
                        queue.offer(words[i]);
                        visited[i] = true;
                        continue;
                    }

                    // 한 글자가 중간에 들어 갔을 경우 한 글자씩 순서에 맞게 확인을 해본다.
                    char[] beforeWord = word.toCharArray();
                    char[] afterWord = words[i].toCharArray();
                    boolean check = false;
                    for (int j = 0, k = 0; j < afterWord.length; j++) {
                        if (beforeWord[k] == afterWord[j]) {
                            k++;
                            continue;
                        }
                        if (!check) {
                            check = true;
                            continue;
                        }
                        if (check) {
                            check = false;
                            break;
                        }
                    }
                    // true로 나오면 1번만 다른 단어 false로 나오면 2번 이상 다른 단어
                    if (check) {
                        queue.offer(words[i]);
                        visited[i] = true;
                    }
                }
            }
        }
    }
}