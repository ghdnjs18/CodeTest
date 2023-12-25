import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        
        System.out.println(stringTokenizer.countTokens());
	}
}