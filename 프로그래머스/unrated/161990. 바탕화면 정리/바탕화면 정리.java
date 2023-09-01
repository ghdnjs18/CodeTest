class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];
        int first_x = 0;
        int last_x = 0;
        int first_y = 0;
        int last_y = 0;


        boolean first = true;
        for (int i = 0; i < wallpaper.length; i++) {
            // 처음 나온 폴더의 위치 선택
            if (first && wallpaper[i].contains("#")) {
                first = false;
                first_x = i;
                first_y = wallpaper[i].indexOf("#");
            }
            // 마지막 줄의 폴더 위치
            if (wallpaper[i].contains("#")) last_x = Math.max(last_x, i);
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