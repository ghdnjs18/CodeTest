import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        // S와 X의 위치 찾기
        int move_x = 0;
        int move_y = 0;
        List<String> x = new ArrayList<>();
        for (int i = 0; i < park.length; i++) {
            String[] str = park[i].split("");
            for (int j = 0; j < str.length; j++) {
                if (str[j].equals("S")) {
                    move_x = i;
                    move_y = j;
                }
                if (str[j].equals("X")) {
                    x.add("x" + i + "y" + j);
                }
            }
        }

        // 강아지 움직이며 위치 확인
        for (String route : routes) {
            String[] move = route.split(" ");
            boolean check = true;
            int temp_x = move_x;
            int temp_y = move_y;
            switch (move[0]) {
                case "E":
                    for (int i = 0; i < Integer.parseInt(move[1]); i++) {
                        if (x.contains("x" + move_x + "y" + (temp_y + 1))) {
                            check = false;
                            break;
                        }
                        temp_y++;
                    }
                    if (move_y + Integer.parseInt(move[1]) >= park[0].length() || !check) break;
                    move_y += Integer.parseInt(move[1]);
                    break;
                case "W":
                    for (int i = 0; i < Integer.parseInt(move[1]); i++) {
                        if (x.contains("x" + move_x + "y" + (temp_y - 1))) {
                            check = false;
                            break;
                        }
                        temp_y--;
                    }
                    if (move_y - Integer.parseInt(move[1]) < 0 || !check) break;
                    move_y -= Integer.parseInt(move[1]);
                    break;
                case "S":
                    for (int i = 0; i < Integer.parseInt(move[1]); i++) {
                        if (x.contains("x" + (temp_x + 1) + "y" + move_y)) {
                            check = false;
                            break;
                        }
                        temp_x++;
                    }
                    if (move_x + Integer.parseInt(move[1]) >= park.length || !check) break;
                    move_x += Integer.parseInt(move[1]);
                    break;
                case "N":
                    for (int i = 0; i < Integer.parseInt(move[1]); i++) {
                        if (x.contains("x" + (temp_x - 1) + "y" + move_y)) {
                            check = false;
                            break;
                        }
                        temp_x--;
                    }
                    if (move_x - Integer.parseInt(move[1]) < 0 || !check) break;
                    move_x -= Integer.parseInt(move[1]);
                    break;
            }
        }

        return new int[]{move_x, move_y};
    }
}