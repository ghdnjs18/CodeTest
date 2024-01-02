import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());

        // 입력값을 배열에 넣어준다.
        String[] strArray = new String[N * 2 - 2];
        for (int i = 0; i < 2 * N - 2; i++) {
            strArray[i] = bufferedReader.readLine();
        }
        bufferedReader.close();

        // 가장 긴 문자열과 가장 짧은 문자열을 찾아 각각 배열에 넣어준다.
        String[] maxStr = new String[2];
        int maxCnt = 0;
        for (String s : strArray) {
            if (s.length() == N - 1) {
                maxStr[maxCnt++] = s;
            }
        }

        // 가장 긴 문자열을 이용해 2개의 원본 문자열 후보를 만든다.
        String[] originStr = new String[2];
        originStr[0] = maxStr[0] + maxStr[1].substring(N-2);
        originStr[1] = maxStr[1] + maxStr[0].substring(N-2);

        // 접두사와 접미사가 같은 문자열을 대비해 접두사 사용 유무를 확인할 배열을 선언한다.
        boolean[] prefix;
        StringBuilder stringBuilder;
        for (String origin : originStr) {
            prefix = new boolean[N];
            stringBuilder = new StringBuilder();
            for (String str : strArray) {
				// 0이면 접두사로 사용할 수 있다. 
				if (origin.indexOf(str) == 0) {
					// 접두사와 접미사 양쪽에 속할 수 있는 경우를 고려한다. 
					if (!prefix[str.length()]) {
						prefix[str.length()] = true;
						stringBuilder.append("P");
					} else stringBuilder.append("S");
				} else {
					// 마지막 문자 비교로 접미사인지 확인한다. 
					if (str.charAt(str.length() - 1) == origin.charAt(origin.length() - 1)) {
						stringBuilder.append("S");
					}
				}
			}
			// 빌더의 길이가 접두사, 접미사의 총 개수와 같아야한다.
			if (stringBuilder.length() == 2 * N - 2) {
				System.out.println(origin);
				System.out.println(stringBuilder.toString());
				break;
			}
        }
    }
}