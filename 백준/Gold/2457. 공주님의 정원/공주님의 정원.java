import java.io.*;
import java.util.*;

public class Main {
    /*
    * 주어진 값 : 꽃의 개수 N, 꽃이 피어있는 기간
    * 조건 : 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록 한다.
    * 목표 : 필요한 꽃의 최소 개수
    * 입력시 꽃이 피는 날짜가 3월 1일 전의 날과 지는 날짜가 12월 이후인 날이 없으면 두 조건을 만족할 수 없어 0을 출력한다.
    * 입력 값을 꽃이 피는 날짜는 빠른 기준 꽃이 지는 날짜는 늦는 기준으로 정렬을 한다.
    * 3월1일을 기준으로 시작해 3월 1일보다 피는 날짜가 빠른 꽃 중에 제일 늦게 지는 꽃을 찾는다.
    * 이후 제일 늦게 지는 꽃보다 피는 날짜가 빠른 꽃 중에 제일 늦게 지는 꽃을 찾는 것을 반복한다.
    * 지는 날이 12월이 되는 꽃을 선택하는 순간 종료하고 횟수를 출력한다.
    * */

    static boolean isBloom, isFall;

    public static void main(String[] args) throws IOException {
        // 꽃의 개수
        int N = readNumber();

        // 꽃이 피어있는 기간 입력
        List<Flower> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int bloomedMonth = readNumber();
            int bloomedDay = readNumber();
            int falledMonth = readNumber();
            int falledDay = readNumber();

            Flower flower = new Flower(bloomedMonth, bloomedDay, falledMonth, falledDay);
            list.add(flower);
            findBloom(flower);
        }

        int result = 0;
        // 2번째 조건에 성립을 하면 꽃선택 시작
        if (isBloom && isFall) {
            // 꽃이 피는 날짜는 빠른 기준 꽃이 지는 날짜는 늦는 기준으로 정렬을 한다.
            list.sort((o1, o2) -> o1.bloomedMonth != o2.bloomedMonth ?
                    o1.bloomedMonth - o2.bloomedMonth : o1.bloomedDay != o2.bloomedDay ?
                    o1.bloomedDay - o2.bloomedDay : o1.falledMonth != o2.falledMonth ?
                    o2.falledMonth - o1.falledMonth : o2.falledDay - o1.falledDay);

            int curMonth = 3;
            int curDay = 1;
            int freMonth = 3;
            int freDay = 1;
            // 3월 1일 보다 빠른 꽃을 선택을 먼저 하고 시작한다.
            int cnt = 1;
            for (Flower flower : list) {
                // 가장 늦게 지는 꽃으로 선택을 한다.
                if (!isValid(flower.bloomedMonth, freMonth, flower.bloomedDay, freDay)) {
                    if (isValid(freMonth, curMonth, freDay, curDay)) {
                        freMonth = curMonth;
                        freDay = curDay;
                        cnt++;
                    }
                }
                // 이전에 선택한 꽃이 지는 시기보다 빠른 피는 시기를 가진 꽃 중에 가장 늦게 지는 꽃을 찾는다.
                if (isValid(flower.bloomedMonth, freMonth, flower.bloomedDay, freDay)) {
                    if (isValid(curMonth, flower.falledMonth, curDay, flower.falledDay)) {
                        curMonth = flower.falledMonth;
                        curDay = flower.falledDay;
                    }
                }
                if (curMonth == 12) {
                    result = cnt;
                    break;
                }
            }

        }

        System.out.println(result);
    }

    static class Flower {
        public int bloomedMonth;
        public int bloomedDay;
        public int falledMonth;
        public int falledDay;

        public Flower(int bloomedMonth, int bloomedDay, int falledMonth, int falledDay) {
            this.bloomedMonth = bloomedMonth;
            this.bloomedDay = bloomedDay;
            this.falledMonth = falledMonth;
            this.falledDay = falledDay;
        }
    }

    private static int readNumber() throws IOException {
        int cur = System.in.read() & 15;
        int next = 0;
        boolean flag = cur == 13;
        if (flag) cur = 0;
        while ((next = System.in.read()) > 32) cur = (cur * 10) + (next & 15);
        return flag ? -cur : cur;
    }

    private static void findBloom(Flower flower) {
        if (flower.bloomedMonth < 3) {
            isBloom = true;
        } else if (flower.bloomedMonth == 3 && flower.bloomedDay == 1) {
            isBloom = true;
        }
        if (flower.falledMonth > 11) {
            isFall = true;
        }
    }

    private static boolean isValid(int freMonth, int curMonth, int freDay, int curDay) {
        return (freMonth < curMonth) || (freMonth == curMonth && freDay <= curDay);
    }
}