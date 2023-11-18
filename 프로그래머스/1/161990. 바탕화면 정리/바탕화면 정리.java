class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        int first_x = Integer.MAX_VALUE;
        int last_x = Integer.MIN_VALUE;
        int first_y = Integer.MAX_VALUE;
        int last_y = Integer.MIN_VALUE;

        for (int i = 0; i < wallpaper.length; i++) {
            if (wallpaper[i].contains("#")) {
                first_x = Math.min(first_x, i);
                last_x = Math.max(last_x, i);
            }
            // 가장 앞줄에 있는 폴더 위치
            first_y = Math.min(first_y, wallpaper[i].indexOf("#") < 0 ? first_y : wallpaper[i].indexOf("#"));
            // 가장 뒤에 있는 폴더 위치
            last_y = Math.max(last_y, wallpaper[i].lastIndexOf("#"));
        }

        answer[0] = first_x;
        answer[1] = first_y;
        answer[2] = last_x + 1;
        answer[3] = last_y + 1;

        return answer;
    }
}