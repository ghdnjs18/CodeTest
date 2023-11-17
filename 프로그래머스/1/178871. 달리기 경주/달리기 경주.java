import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        LinkedHashMap<String, Integer> rank = new LinkedHashMap<>();
        LinkedHashMap<Integer, String> play = new LinkedHashMap<>();

        // 선수의 등수와, 등수의 선수 2개의 map 생성
        for (int i = 0; i < players.length; i++) {
            rank.put(players[i], i);
            play.put(i, players[i]);
        }

        // 호명 될 때 마다 인덱스를 한칸씩 앞으로 옮김
        for (String calling : callings) {
            // 호명 된 선수 위치
            int index = rank.get(calling);

            // 호명 된 선수 앞의 선수
            String frontP = play.get(index - 1);

            // 선수의 순위 변경
            rank.put(calling, index - 1);
            rank.put(frontP, index);

            // 순위의 선수 변경
            play.put(index - 1, calling);
            play.put(index, frontP);
        }

        return play.values().toArray(new String[0]);
    }
}