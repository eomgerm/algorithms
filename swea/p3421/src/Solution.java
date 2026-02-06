import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static HashSet<Integer> bad;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            bad = new HashSet<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                int pair = (1 << a) | (1 << b);
                if (bad.contains(pair)) continue;
                bad.add(pair);
            }

            answer = 0;
            dfs(0, 0);

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int cnt, int bits) {
        for (int b : bad) {
            if ((bits & b) == b) return;
        }

        if (cnt == N) {
            answer++;
            return;
        }

        dfs(cnt + 1, bits | (1 << cnt));
        dfs(cnt + 1, bits);
    }
}
