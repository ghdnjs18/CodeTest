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
    * */
    
    static int n, m;
    static int[] truth;
    static int[] parent;
    static List<List<Integer>> attendee;
    static boolean[] knowTruth;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(st.nextToken());
        truth = new int[len];
        for(int i=0; i<len; i++){
            truth[i] = Integer.parseInt(st.nextToken());
        }

        attendee = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            attendee.add(new ArrayList<>());
            len = Integer.parseInt(st.nextToken());
            for(int j=0; j<len; j++){
                int id = Integer.parseInt(st.nextToken());
                attendee.get(i).add(id); //파티에 참석하는 사람
            }
        }

        knowTruth = new boolean[n+1];
        parent = new int[n+1];
        
        //진실을 알고 있는 사람
        for(int t:truth){
            knowTruth[t] = true;
        }

        for(int i=0; i<=n; i++){
            parent[i] = i;
        }

        //같이 파티에 있었던 사람 union
        for(int i=0; i<m; i++){
            for(int j=0; j<attendee.get(i).size()-1; j++){
                union_node(attendee.get(i).get(j), attendee.get(i).get(j+1));
            }
        }

        //진실을 아는 사람의 parent는 진실을 알게 됨
        for(int i=1; i<=n; i++){
            if(knowTruth[i]){
                int parent = find_parent(i);
                knowTruth[parent] = true;
            }
        }

        int answer = 0;
        //파티 참석자의 parent가 진실을 알고있다면 패스
        for(int i=0; i<m; i++){
            boolean flag = true;
            for(int j=0; j<attendee.get(i).size(); j++){
                int p = attendee.get(i).get(j);
                int parent = find_parent(p);
                if(knowTruth[parent]){
                    flag = false;
                    break;
                }
            }
            if(flag)
                answer++;
        }

        System.out.println(answer);
        
    }

    public static int find_parent(int u){
        if(parent[u]==u) return u;
        return parent[u] = find_parent(parent[u]);
    }

    public static void union_node(int u, int v){
        u = find_parent(u);
        v = find_parent(v);

        if(u>v){
            int temp = u;
            u = v;
            v = temp;
        }

        if(u==v) return;
        else parent[v] = u;
    }
}