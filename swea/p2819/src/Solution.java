import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static int[][] grid;
    static Set<String> nums;
    static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            grid = new int[4][4];
            for (int r = 0; r < 4; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < 4; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            nums = new HashSet<>();
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    dfs(r, c, 0, "");
                }
            }

            sb.append("#").append(t + 1).append(" ").append(nums.size()).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int r, int c, int cnt, String numStr) {
        if (cnt == 7) {
            nums.add(numStr);
            return;
        }

        numStr += grid[r][c];

        for (int  i = 0; i < 4; i++) {
            int nR = r + dr[i];
            int nC = c + dc[i];

            if (nR < 0 || nR >= 4 || nC < 0 || nC >= 4) continue;
            dfs(nR, nC, cnt + 1, numStr);
        }
    }
}
