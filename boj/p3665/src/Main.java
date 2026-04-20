import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 3665 최종 순위
  https://www.acmicpc.net/problem/3665
*/

public class Main {
    static List<Integer>[] g;
    static int[] in;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 코드를 작성하세요.

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());

            g = new List[n + 1];
            for (int i = 0; i <= n; i++) {
                g[i] = new ArrayList<>();
            }

            in = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int team = Integer.parseInt(st.nextToken());
                in[team] = i;
                for (int j = 1; j <= n; j++) {
                    if (j != team && !g[j].contains(team))
                        g[team].add(j);
                }
            }

            int m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (g[a].contains(b)) {
                    g[a].remove(Integer.valueOf(b));
                    g[b].add(a);
                    in[b]--;
                    in[a]++;
                } else {
                    g[b].remove(Integer.valueOf(a));
                    g[a].add(b);
                    in[a]--;
                    in[b]++;
                }
            }

            sb.append(topologySort()).append("\n");
        }

        System.out.println(sb);
    }

    static String topologySort() {
        StringBuilder result = new StringBuilder();

        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) {
                queue.addLast(i);
            }
        }

        for (int i = 0; i < n; i++) {
            if (queue.size() > 1) return "?";

            if (queue.isEmpty()) return "IMPOSSIBLE";

            int cur = queue.removeFirst();
            result.append(cur).append(" ");

            for (int next : g[cur]) {
                in[next]--;
                if (in[next] == 0) {
                    queue.addLast(next);
                }
            }
        }

        return result.toString();
    }
}