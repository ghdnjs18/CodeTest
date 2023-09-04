import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();

        // 기준 날짜를 숫자로 비교하기 위해서 .을 빼고 숫자로 변경한다.
        int todays = Integer.parseInt(today.replace(".",""));

        // 약관 종류와 유효기간으로 나누어 계산할 수 있도록 map에 담는다.
        Map<String, Integer> terms_map = new HashMap<>();
        for (String term : terms) {
            String[] t = term.split(" ");
            terms_map.put(t[0], Integer.parseInt(t[1]));
        }

        // map에 담긴 terms를 privacies에 더하여 todays보다 작으면 answer에 추가한다.
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");

            int temp = terms_map.get(privacy[1]);
            String day = privacy[0].replace(".", "");
            int temp_day = Integer.parseInt(day);
            // 유효기간을 먼저 계산
            if (temp / 12 > 0) {
                temp_day += temp / 12 * 10000 + temp % 12 * 100;
            } else {
                temp_day += temp * 100;
            }

            // 유효기간을 계산한 달이 12달을 넘었을 경우 한번 더 년 수를 더해주고 12달 뺀다.
            if ((Integer.parseInt(day.substring(4, 6)) + temp % 12) / 13 > 0) {
                temp_day += 10000 - 1200;
            }

            if (todays >= temp_day) answer.add(i+1);
        }


        return answer.stream().mapToInt(i -> i).toArray();
    }
}