import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Set<String> phoneList = new TreeSet<>();
        Set<String> tmpList = new TreeSet<>();

        for (String phoneNumber : phone_book) {
            phoneList.add(phoneNumber);
            tmpList.add(phoneNumber);
        }

        for (String s : phoneList) {
            tmpList.remove(s);
            if (tmpList.stream().iterator().hasNext()) {
                if (tmpList.stream().iterator().next().startsWith(s)) return answer = false;
            }
        }
        return answer;
    }
}