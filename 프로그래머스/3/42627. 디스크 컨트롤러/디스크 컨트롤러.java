class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        // 배열 정렬
        int[] temp = {};
        int minIndex = 0;
        for (int i = 0; i < jobs.length - 1; i++) {
            minIndex = i;

            for (int j = i + 1; j < jobs.length; j++) {
                if (jobs[j][0] < jobs[minIndex][0]) {
                    minIndex = j;
                } else if (jobs[j][0] == jobs[minIndex][0]) {
                    if (jobs[j][1] < jobs[minIndex][1]) {
                        minIndex = j;
                    }
                }
            }

            temp = jobs[minIndex];
            jobs[minIndex] = jobs[i];
            jobs[i] = temp;
        }

        // 작업의 소요시간으로 정렬
        int time = jobs[0][0] + jobs[0][1];
        answer += jobs[0][1];
        for (int i = 0; i < jobs.length - 1; i++) {
            int minTime = Integer.MAX_VALUE;
            minIndex = i;

            for (int j = i + 1; j < jobs.length; j++) {
                if (jobs[j][0] <= time) {
                    if (jobs[j][1] < minTime) {
                        minTime = jobs[j][1];
                        minIndex = j;
                    }
                } else {
                    break;
                }
            }
            if (minIndex == i) minIndex = i + 1;

            temp = jobs[minIndex];
            jobs[minIndex] = jobs[i + 1];
            jobs[i + 1] = temp;

            if (time < temp[0]) {
                answer += temp[1];
            } else {
                answer += time - temp[0] + temp[1];
            }
            time += temp[1];
        }

        return answer / jobs.length;
    }
}