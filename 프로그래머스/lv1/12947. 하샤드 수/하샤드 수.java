class Solution {
    public boolean solution(int x) {
        int temp = x; // x의 값을 수정하기 위한 변수 생성
        int total = 0;
        
        while (true) {
            total += temp % 10; // 일의 자리를 total에 더한다.
            // 마지막 일의 자리를 더했으면 하샤드 수인지 구분하여 리턴
            if (temp < 10) return x % total == 0? true: false;
            temp = temp / 10; // 일의 자리 삭제
        }
    }
}