import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();

        // 0,1로 체스판의 흰,검 형태로 만들어준다.
        int[][] map = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == j % 2) {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        // 테스트 케이스 만큼 반복한다.
        int T = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int startY = stringTokenizer.nextToken().charAt(0) - 'A';
            int startX = Math.abs(Integer.parseInt(stringTokenizer.nextToken()) - 8);
            int endY = stringTokenizer.nextToken().charAt(0) - 'A';
            int endX = Math.abs(Integer.parseInt(stringTokenizer.nextToken()) - 8);

            // 좌표의 값이 다르면 이동할 수 없다.
            if (map[startX][startY] != map[endX][endY]) {
                stringBuilder.append("Impossible\n");
                continue;
            }
            // 좌표의 값이 일치하면 이동을 안해도 된다.
            if (startX == endX && startY == endY) {
                stringBuilder.append("0 " + (char)(startY + 'A') + " " + (8 - startX) + "\n");
                continue;
            }

            // 체스말이 움직인 경로 체크
            boolean[][] visited = new boolean[8][8];
            // 체스말이 움직이기 위한 포인트
            Queue<int[]> queue = new LinkedList<>();
            // 비숍은 체스판의 모든 장소를 2번이면 갈 수 있기 때문에 시작 위치와 도착 위치에서만 움직이면 동선이 파악가능하다.
            queue.offer(new int[]{startX, startY});
            queue.offer(new int[]{endX, endY});
            // 한번에 움직여서 갈 수 있는 장소인지 확인
            boolean first = true;
            while (!queue.isEmpty()) {
                int[] point = queue.poll();
                int x = point[0];
                int y = point[1];
                
                // 반환점 좌표를 확인하기 위한 변수
                int checkX = 0;
                int checkY = 0;
                // 움직이기전 좌표 표시
                visited[x][y] = true;
                // 대각선 4방향 움직임
                for (int j = 0; j < 4; j++) {
                    int nextX = x;
                    int nextY = y;
                    // 체스판 끝까지 전부 움직여 본다.
                    while (true) {
                        nextX += dx[j];
                        nextY += dy[j];
                        if (nextX >= 0 && nextY >= 0 && nextX < 8 && nextY < 8) {
                            // 움직일 때마다 경로 좌표 표시
                            if (!visited[nextX][nextY]) {
                                visited[nextX][nextY] = true;
                            } else { // 도착 지점에서 움직였을 경우, 교차지점 반환점이다.
                                checkX = nextX;
                                checkY = nextY;
                                break;
                            }
                            continue;
                        }
                        break;
                    }
                }
                
                // 도착 지점이 움직인 경로로 확인됐을때,
                if (visited[endX][endY]) {
                    // 한번에 왔는지, 두번째에 왔는지에 따라 결과 출력한다.
                    // 한번에 도착지점에 왔을 경우, 도착 지점에서 움직이지 않도록 바로 종료한다.
                    if (first) {
                        stringBuilder.append("1 " + (char)(startY + 'A') + " " + (8 - startX));
                        stringBuilder.append(" " + (char)(endY + 'A') + " " + (8 - endX) + "\n");
                        break;
                    } else {
                        stringBuilder.append("2 " + (char)(startY + 'A') + " " + (8 - startX));
                        stringBuilder.append(" " + (char)(checkY + 'A') + " " + (8 - checkX));
                        stringBuilder.append(" " + (char)(endY + 'A') + " " + (8 - endX) + "\n");
                    }
                }
                // 시작 위치에서 출력이 안되면 도착 위치 순서일때 출력을 해야한다.
                first = false;
            }
        }

        System.out.println(stringBuilder);
    }
}