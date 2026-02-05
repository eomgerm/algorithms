import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Solution {
    static int N = 16;
    static int[][] maze = new int[N][N];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = 10;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        for (int t = 0; t < T; t++) {
            int sR = 0, sC = 0;
            int eR = 0, eC = 0;

            br.readLine();
            for (int r = 0; r < N; r++) {
                String[] row = br.readLine().split("");
                for (int c = 0; c < N; c++) {
                    maze[r][c] = Integer.parseInt(row[c]);
                    if (maze[r][c] == 2) {
                        sR = r;
                        sC = c;
                    } else if (maze[r][c] == 3) {
                        eR = r;
                        eC = c;
                    }
                }
            }

            ArrayDeque<int[]> stack = new ArrayDeque<>();
            boolean[][] visited = new boolean[N][N];

            stack.addFirst(new int[] {sR, sC});
            visited[sR][sC] = true;
            boolean reachable = false;

            while (!stack.isEmpty()) {
                int[] cur = stack.removeFirst();
                int cR = cur[0];
                int cC = cur[1];

                if (cR == eR && cC == eC) {
                    reachable = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nR = cR + dr[i];
                    int nC = cC + dc[i];

                    if (nR < 0 || nR >= 16 || nC < 0 || nC >= 16) continue;
                    if (visited[nR][nC]) continue;
                    if (maze[nR][nC] == 1) continue;

                    stack.addFirst(new int[] {nR, nC});
                    visited[nR][nC] = true;
                }
            }

            sb.append("#").append(t + 1).append(" ").append(reachable ? 1 : 0).append("\n");
        }

        System.out.println(sb);
    }

}
