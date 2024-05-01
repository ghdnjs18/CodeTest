import java.io.*;
import java.util.*;

public class Main {
    
    /*
    * 주어진 값 : 사람의 수 N, 파티의 수 M, 진실을 알고 있는 사람, 파티 별 참석하는 사람
    * 조건 : 같은 파티에 진실을 알고 있는 사람이 있으면 모두 진실을 알게된다.
    * 목표 : 과장된 이야기를 할 수 있는 파티의 최대수
    * 사람의 수만큼 배열을 만들어 사람들이 진실을 아는 여부를 입력한다.
    * 파티를 입력하면서 해당 파티에 진실을 아는 사람이 있는지 확인을 한다.
    * 진실을 알고 있는 사람이 한명이라도 있으면 해당 파티의 사람들의 진실 여부를 체크해준다.
    * 이후 파티배열을 순환하면서 진실을 아는 사람이 없으면 횟수를 헤아리고 출력해준다.
    * -> 나중에 진실을 아는 사람이 있을 경우 그전 파티에 있는 사람은 확인이 안된다.
    * -> 추가적으로 사람간의 연결을 그래프로 만들어서 추가적으로 진실여부를 판단해야한다.
    * */

    static List<Set<Integer>> persons = new ArrayList<>();
    static boolean[] truePerson;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 사람의 수
        int N = Integer.parseInt(stringTokenizer.nextToken());
        // 파티의 수
        int M = Integer.parseInt(stringTokenizer.nextToken());

        // 사람 관계
        for (int i = 0; i <= N; i++) {
            persons.add(new HashSet<>());
        }

        // 진실을 알고 있는 사람
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int trueCnt = Integer.parseInt(stringTokenizer.nextToken());
        truePerson = new boolean[N + 1];
        for (int i = 0; i < trueCnt; i++) {
            truePerson[Integer.parseInt(stringTokenizer.nextToken())] = true;
        }

        List<List<Integer>> party = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            party.add(new ArrayList<>());
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            boolean flag = false;
            // 파티에 참석한 사람
            int partyPerson = Integer.parseInt(stringTokenizer.nextToken());
            for (int j = 0; j < partyPerson; j++) {
                int person = Integer.parseInt(stringTokenizer.nextToken());
                party.get(i).add(person);

                // 해당 파티에 진실을 아는 사람이 있는지 확인
                if (truePerson[person]) {
                    flag = true;
                }
            }

            // 진실을 아는 사람이 있으면 해당 파티의 사람들은 모두 진실을 알게된다.
            if (flag) {
                for (int person : party.get(i)) {
                    truePerson[person] = true;
                }
            }

            // 사람간의 연관관계를 추가해준다.
            for (int personA : party.get(i)) {
                for (int personB : party.get(i)) {
                    persons.get(personA).add(personB);
                    persons.get(personB).add(personA);
                }
            }
        }

        // 사람들의 관계에서 진실된 이야기를 확장 시킨다.
//        int a = 0;
//        for (Set<Integer> person : persons) {
//            System.out.println(a++);
//            for (Integer i : person) {
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }

        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (truePerson[i]) {
                findTrue(i);
            }

        }

        // 고장된 이야기 횟수 확인
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            boolean flag = false;
            for (int person : party.get(i)) {
                if (truePerson[person]) {
                    break;
                } else {
                    flag = true;
                }
            }
            // 진실을 아는 사람이 없으면 과장된 이야기
            if (flag) cnt++;
        }

        System.out.println(cnt);
    }

    private static void findTrue(int cur) {

        visited[cur] = true;

        for (int next : persons.get(cur)) {
            if (!visited[next] && truePerson[cur]) {
                truePerson[next] = true;
                findTrue(next);
            }
        }
    }
}