import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        StringBuilder builder = new StringBuilder();

        // 모든 수를 4자리로 만들어 비교를 하기 위해 리스트 만들기
        List<Integer> list = new ArrayList<>();
        for (int number : numbers) {
            if (number < 10) {
                list.add(number * 1111);
            } else if (number < 100) {
                list.add(number * 101);
            } else if (number < 1000) {
                list.add(number * 1001 / 100);
            } else {
                list.add(number);
            }
        }
        // 복사본 배열 만들기
        int[] tmepNumbers = list.stream().mapToInt(x -> x).toArray();

        // 4자리로 만든 숫자들로 비교를 하여 선택정렬을 이용해서 내림차순 정렬
        int temp = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < numbers.length; j++) {
                if (tmepNumbers[maxIndex] < tmepNumbers[j]) {
                    maxIndex = j;
                }
            }

            temp = numbers[maxIndex];
            numbers[maxIndex] = numbers[i];
            numbers[i] = temp;

            temp = tmepNumbers[maxIndex];
            tmepNumbers[maxIndex] = tmepNumbers[i];
            tmepNumbers[i] = temp;
        }

        // 정렬된 배열을 문자열에 추가
        for (int job : numbers) {
            // 맨 앞자리가 0일 경우 해당 숫자로 교체 처리
            if (builder.toString().equals("0")) {
                builder = new StringBuilder(String.valueOf(job));
            } else {
                builder.append(job);
            }
            // if (answer.equals("0")) {
            //     answer = String.valueOf(job);
            // } else {
            //     answer += job;
            // }
        }
         
        return answer = builder.toString();
    }
}