import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] things = new int[N][2];
            for (int[] thing : things) {
                st = new StringTokenizer(br.readLine());
                thing[0] = Integer.parseInt(st.nextToken());
                thing[1] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N + 1][K + 1];
            Arrays.fill(dp[0], 0);
            for (int r = 0; r <= N; r++) {
                dp[r][0] = 0;
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    int v = things[i - 1][0];
                    int c = things[i - 1][1];

                    if (v > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v] + c);
                    }
                }
            }

            sb.append("#").append(t + 1).append(" ").append(dp[N][K]).append("\n");
        }

        System.out.println(sb);
    }
}
