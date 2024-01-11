import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(bufferedReader.readLine());

        List<String> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String str = bufferedReader.readLine();
            if (!list.contains(str)) {
                list.add(str);
            }
        }

        list = list.stream().sorted().sorted((o1, o2) -> o1.length() - o2.length()).collect(Collectors.toList());

        for (String s : list) {
            System.out.println(s);
        }
    }
}