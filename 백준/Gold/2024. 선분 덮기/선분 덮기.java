import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 좌표 M (1 <= M <= 50000), 임의의 좌표(L, R) ?개 (-50000 <= L, R <= 50000)
    * 조건 : 주어지는 좌표의 개수는 모른다. (0 0 이력시 종료),
    * 목표 : (0, M)을 모두 덮는 선의 개수, 선을 덮는 방법이 없으면 0 출력
    * (0, M)의 선분을 덮을려면 정확하게 겹쳐서 지나가야 하므로 마이너스 좌표는 필요가 없이 0으로 좌표를 변경한다.
    * 시작 점과 끝 점이 같은 좌표는 점이 찍혀 선분을 가릴 수 없어 제외 시켜준다.
    * 주어진 좌표를 시작점과 끝점 각각 오름차순으로 정렬해서 시작할수 있는 작은 시작점에서 가장 큰 끝점을 선택해주는 것을 반복하여 선을 덮어준다.
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(bufferedReader.readLine());

        List<int[]> list = new ArrayList<>();
        while (true) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int L = Integer.parseInt(stringTokenizer.nextToken());
            int R = Integer.parseInt(stringTokenizer.nextToken());
            if (L > R) {
                int temp = L;
                L = R;
                R = temp;
            }

            if (L == 0 && R == 0) {
                break;
            }
            if (L != R && L <= M && 0 <= R) {
                list.add(new int[]{L, R});
            }
        }

        list.sort((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        int start = 0;
        int end = 0;
        int idx = 0;
        int cnt = 0;
        while (true) {

            boolean flag = false;
            // 시작 점보다 낮은 좌표중에 가장 높은 끝점 찾기
            for (int i = idx; i < list.size(); i++) {
                idx++;
                if (list.get(i)[0] <= start) {
                    if (list.get(i)[1] > end) {
                        end = list.get(i)[1];
                        flag = true;
                    }
                } else {
                    break;
                }
            }

            // 끝점이 변경됐으면 시작점을 변경해주고 횟수를 올려준다.
            if (flag) {
                start = end;
                cnt++;
            // 시작할 수 있는 좌표가 없으면 종료
            } else {
                cnt = 0;
                break;
            }

            // 끝점에 도달했으면 종료
            if (M <= end) {
                break;
            }

        }

        System.out.println(cnt);
    }
}