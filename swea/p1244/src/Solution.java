import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static char[] result, nums;
    static String answer;
    static Set<String> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            nums = st.nextToken().toCharArray();
            int count = Integer.parseInt(st.nextToken());

            result = nums.clone();
            answer = "000000";
            visited = new HashSet<>();
            dfs(count);

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int count) {
        if (count == 0) {
            String cur = new String(result);
            if (cur.compareTo(answer) > 0) {
                answer = cur;
            }
            return;
        }

        if (visited.contains(new String(result) + " " + count)) {
            return;
        }
        visited.add(new String(result) + " " + count);

        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                char tmp = result[j];
                result[j] = result[i];
                result[i] = tmp;

                dfs(count - 1);

                tmp = result[i];
                result[i] = result[j];
                result[j] = tmp;
            }
        }
    }
}
