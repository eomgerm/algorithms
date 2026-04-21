import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2056 작업
  https://www.acmicpc.net/problem/2056
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.
        int N = Integer.parseInt(br.readLine());

        List<Integer>[] g = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        int[] indegree = new int[N + 1];
        int[] cost = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());

            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                int t = Integer.parseInt(st.nextToken());
                g[t].add(i);
                indegree[i]++;
            }
        }

        int[] dp = new int[N + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.addLast(i);
                dp[i] = cost[i];
            }
        }

        while (!queue.isEmpty()) {
            int p = queue.removeFirst();

            for (int n : g[p]) {
                dp[n] = Math.max(dp[n], dp[p] + cost[n]);

                indegree[n]--;
                if (indegree[n] == 0) {
                    queue.addLast(n);
                }
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}