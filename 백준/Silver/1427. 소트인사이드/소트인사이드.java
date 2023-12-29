import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        
        String str = bufferedReader.readLine();

        str = Arrays.stream(str.split("")).sorted(Comparator.reverseOrder()).collect(Collectors.joining());

        System.out.print(str);
    }
}