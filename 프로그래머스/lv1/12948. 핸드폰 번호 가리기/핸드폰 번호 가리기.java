class Solution {
    public String solution(String phone_number) {
        // substring()을 이용해 전화번호 뒷 4자리 제외한 숫자를 가져온다.
        // 가져온 데이터에 replaceAll() 하여 모든 문자를 *로 변경한다.
        // 이후 substring()으로 뒷 4자리를 가져와 다시 붙여준다.
        String answer = phone_number.substring(0,phone_number.length()-4).replaceAll("\\w","*") + 
                        phone_number.substring(phone_number.length()-4, phone_number.length());
        
        return answer;
    }
}