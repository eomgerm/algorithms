import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new  StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            init(N);
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                union(x, y);
            }

            Set<Integer> set = new HashSet<>();
            for (int i = 1; i < N + 1; i++) {
                set.add(find(i));
            }

            sb.append("#").append(t + 1).append(" ").append(set.size()).append("\n");
        }

        System.out.println(sb);
    }

    static void init(int N) {
        parents = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }
    }

    static int find(int n) {
        if (parents[n] != n) {
            parents[n] = find(parents[n]);
        }

        return parents[n];
    }

    static void union(int x, int y) {
        parents[find(x)] = find(y);
    }
}
