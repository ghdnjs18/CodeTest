import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> list;
    static int color[];
    static boolean check = false;

        public static void main(String args[]) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int t = Integer.parseInt(br.readLine());

            while(t-->0){
                list = new ArrayList<>();

                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());

                for(int i=0; i<=n; i++)
                    list.add(new ArrayList<>());

                for(int i=0; i<m; i++){
                    st = new StringTokenizer(br.readLine());
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    list.get(a).add(b);
                    list.get(b).add(a);
                }

                color = new int[n+1];
                check = false;

                for(int i=1; i<=n; i++){
                    if(color[i] == 0)
                        bfs(i);

                    if(check)
                        break;

                }

                if(check)
                    bw.write("impossible\n");
                else
                    bw.write("possible\n");

            }

            bw.close();
        }

        public static void bfs(int start){
            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            color[start] = 1;

            while(!q.isEmpty()){
                int cur = q.poll();

                for(int next : list.get(cur)){
                    if(color[next] == 0){
                        q.add(next);
                        color[next] = color[cur] * -1;
                    }else if(color[next] + color[cur] != 0){
                        check = true;
                        return;
                    }
                }
            }
        }


    }