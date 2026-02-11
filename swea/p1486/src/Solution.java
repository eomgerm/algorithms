import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int answer, N, B;
    static int[] heights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            heights = new int[N];
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }

            answer = Integer.MAX_VALUE;
            dfs(0, 0);

            sb.append("#").append(t+1).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int cnt, int hSum) {
        if (hSum >= B && answer <= hSum - B) return;
        if (cnt == N) {
            if (hSum >= B) answer = hSum - B;
            return;
        }

        dfs(cnt + 1, hSum + heights[cnt]);
        dfs(cnt + 1, hSum);
    }
}
