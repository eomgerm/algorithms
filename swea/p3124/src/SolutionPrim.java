import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SolutionPrim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            List<int[]>[] graph = new List[V + 1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                graph[a].add(new int[] {b, w});
                graph[b].add(new int[] {a, w});
            }

            long mst = 0;
            boolean[] v = new boolean[V + 1];
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));
            pq.add(new int[] {1, 0});

            while(!pq.isEmpty()) {
                int[] cur = pq.poll();
                int cN = cur[0];
                int w = cur[1];

                if (v[cN]) continue;
                v[cN] = true;
                mst += w;

                for (int[] e : graph[cN]) {
                    if (v[e[0]]) continue;
                    pq.add(e);
                }
            }

            sb.append("#").append(t + 1).append(" ").append(mst).append("\n");
        }

        System.out.println(sb);
    }
}
