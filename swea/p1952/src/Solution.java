import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] prices = new int[4];
            for (int i = 0; i < 4; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int[] dp = new int[13];
            for (int i = 1; i <= 12; i++) {
                int p = Integer.parseInt(st.nextToken());
                    int dayMonth = Math.min(p * prices[0], prices[1]);
                    if (i < 3) {
                        dp[i] = Math.min(dp[i - 1] + dayMonth, prices[3]);
                    } else {
                        dp[i] = Math.min(dp[i - 1] + dayMonth, Math.min(dp[i - 3] + prices[2], prices[3]));
                    }
            }

            sb.append("#").append(t + 1).append(" ").append(dp[12]).append("\n");
        }

        System.out.println(sb);
    }
}
