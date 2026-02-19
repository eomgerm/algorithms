import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Solution {
    static HashMap<String, Integer> memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String start = br.readLine();

            memo = new HashMap<>();
            sb.append("#").append(t + 1).append(" ").append(solve(start)).append(" ").append("\n");
        }

        System.out.println(sb);
    }

    static int solve(String num) {
        if (Integer.parseInt(num) < 10) {
            return 0;
        }

        if (memo.containsKey(num)) {
            return memo.get(num);
        }

        int maxTurn = 0;
        for (int i = 1; i < 1 << (num.length() - 1); i++) {
            String p = "";
            int m = 1;
            for (int j = 0; j < num.length(); j++) {
                p += num.charAt(j);
                if ((i & (1 << j)) != 0) {
                    m *= Integer.parseInt(p);
                    p = "";
                }
            }
            m *= Integer.parseInt(p);

            maxTurn = Math.max(maxTurn, solve(m + "") + 1);
        }

        memo.put(num, maxTurn);
        return maxTurn;
    }
}
