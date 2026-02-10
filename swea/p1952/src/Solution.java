import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] prices, plans;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            prices = new int[4];
            for (int i = 0; i < 4; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            plans = new int[12];
            for (int i = 0; i < 12; i++) {
                plans[i] = Integer.parseInt(st.nextToken());
            }

            answer = Integer.MAX_VALUE;
            solve(0, 0);

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void solve(int cnt, int cost) {
        if (cost > answer) return;

        if (cnt > 11) {
            answer = cost;
            return;
        }

        if (cnt == 0) solve(cnt + 12, cost + prices[3]);
        solve(cnt + 1, cost + prices[0] * plans[cnt]);
        solve(cnt + 1, cost + prices[1]);
        if (cnt < 10) solve(cnt + 3, cost + prices[2]);
    }
}
