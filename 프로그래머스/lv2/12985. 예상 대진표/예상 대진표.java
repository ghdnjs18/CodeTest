class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1; // 첫 라운드부터 시작 하기에 1로 선언해준다.

        // 계산하기 쉽게 하기 위해 a가 무조건 작은 수로 되도록 변경해준다.
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (true) {
            // a가 홀 수면서 b와 1이 차이날 때 대진에서 만난 시점으로 종료하고 나온다.
            if (a % 2 != 0 && a + 1 == b) break;
            
            // a와 b를 반복해서 2로 나누고 홀 수 일때는 1을 더해준다.
            if (a % 2 == 0) {
                a = a / 2;
            } else {
                a = a / 2 + 1;
            }

            if (b % 2 == 0) {
                b = b / 2;
            } else {
                b = b / 2 + 1;
            }

            answer++;
        }

        return answer;
    }
}