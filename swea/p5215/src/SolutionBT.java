import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionBT {
    static int N, L;
    static int[][] ingre;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            ingre = new int[N][2];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                ingre[i][0] = Integer.parseInt(st.nextToken());
                ingre[i][1] = Integer.parseInt(st.nextToken());
            }

            answer = 0;
            dfs(0, 0, 0);

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int cnt, int score, int c) {
        if (c > L) return;

        if (cnt == N) {
            answer = Math.max(answer, score);

            return;
        }

        dfs(cnt + 1, score + ingre[cnt][0], c + ingre[cnt][1]);
        dfs(cnt + 1, score, c);
    }
}
