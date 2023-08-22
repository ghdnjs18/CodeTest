import java.util.Calendar;

class Solution {
    public String solution(int a, int b) {
        String answer = "";

        // 달마다 날짜를 배열
        int[] day = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // 반복문으로 a(월) 값의 범위에 따라 총 월의 날짜를 계산해주고
        int cnt = 0;
        for (int i = 0; i < a - 1; i++) {
            cnt += day[i];
        }
        // 계산한 값에 b(일)을 추가하였습니다.
        cnt += b;

        // 그리고 월화수목금토일 이 반복되니까 나머지를 switch case 조건으로 주었습니다.
        switch (cnt % 7) {
            case 0 : answer = "THU"; break;
            case 1 : answer = "FRI"; break;
            case 2 : answer = "SAT"; break;
            case 3 : answer = "SUN"; break;
            case 4 : answer = "MON"; break;
            case 5 : answer = "TUE"; break;
            case 6 : answer = "WED"; break;
        }
        
        return answer;
    }
}