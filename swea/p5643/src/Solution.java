import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static List<Integer>[] taller, smaller;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            taller = new List[N + 1];
            smaller = new List[N + 1];

            for (int i = 1; i < N + 1; i++) {
                taller[i] = new ArrayList<>();
                smaller[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                taller[a].add(b);
                smaller[b].add(a);
            }

            int answer = 0;
            for (int i = 1; i < N + 1; i++) {
                int tallerCnt = count(i, true);
                int smallerCnt = count(i, false);

                if (tallerCnt + smallerCnt == N - 1) {
                    answer++;
                }
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static int count(int start, boolean t) {
        List<Integer>[] graph = t ? taller : smaller;

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        stack.addFirst(start);
        visited[start] = true;

        int count = 0;
        while (!stack.isEmpty()) {
            int cur = stack.removeFirst();

            for (int next : graph[cur]) {
                if (visited[next]) continue;

                count++;
                stack.addFirst(next);
                visited[next] = true;
            }
        }

        return count;
    }
}
