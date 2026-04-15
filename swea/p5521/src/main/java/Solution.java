import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // TODO: 풀이를 작성하세요.

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            List<Integer>[] g = new List[N + 1];
            for (int i =1; i <= N; i++) {
                g[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                g[a].add(b);
                g[b].add(a);
            }

            Deque<int[]> queue = new ArrayDeque<>();
            boolean[] v = new boolean[N + 1];
            queue.add(new int[] {1, 0});
            v[1] = true;

            int answer = 0;
            while (!queue.isEmpty()) {
                int[] cur = queue.removeFirst();

                int c = cur[0];
                int d = cur[1];

                if (d == 2) continue;

                for (int n : g[c]) {
                    if (v[n]) continue;
                    answer++;
                    queue.add(new int[] {n, d + 1});
                    v[n] = true;
                }
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
