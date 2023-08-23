class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] wording = {"aya", "ye", "woo", "ma"};

        // babbling을 순회하면서 확인을 한다.
        for (int i = 0; i < babbling.length; i++) {
            // wording을 순회히면서 발음에 맞는 옹아리가 있을 경우 숫자로 변경해 표시해준다.
            for (int j = 0; j < wording.length; j++) {
                babbling[i] = babbling[i].replaceAll(wording[j], String.valueOf(j+1));
            }
        }

        // 발음을 표시한 옹아리를 순회한다.
        for (String str : babbling) {
            // 숫자로만 표시 되어 있으면 옹아리가 발음 할 수 있는 것
            if (str.matches("^[\\d]*$")) {
                // 같은 발음은 연속하지 못하기 때문에 연속 된 숫자를 제외하고 횟수를 헤아린다.
                if (str.contains("11")) continue;
                else if (str.contains("22")) continue;
                else if (str.contains("33")) continue;
                else if (str.contains("44")) continue;
                else answer++;
            }
        }

        return answer;
    }
}