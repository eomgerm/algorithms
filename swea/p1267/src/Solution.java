import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < 10; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());

            List<Integer>[] graph = new List[V + 1];
            for (int i = 1; i < V + 1; i++) {
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int[] indgree = new int[V + 1];
            for (int i = 0; i < E; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                indgree[b]++;

                graph[a].add(b);
            }

            ArrayDeque<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i < V + 1; i++) {
                if (indgree[i] == 0) queue.addLast(i);
            }

            List<Integer> result = new ArrayList<>();
            while (!queue.isEmpty()) {
                int cur = queue.removeFirst();
                result.add(cur);

                for (int next : graph[cur]) {
                    indgree[next]--;
                    if (indgree[next] == 0) {
                        queue.add(next);
                    }
                }
            }

            sb.append("#").append(t + 1).append(" ");
            for (int i : result) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
