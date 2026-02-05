import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
* SWEA 7733: 치즈 도둑
* */
public class Solution {
    static int N;
    static int[][] cheese;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int answer = 0;
            N = Integer.parseInt(br.readLine());
            cheese = new int[N][N];

            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    cheese[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            for (int d = 0; d <= 100; d++) {
                int cnt = 0;
                boolean[][] visited = new boolean[N][N];
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (visited[r][c]) continue;
                        if (cheese[r][c] <= d) continue;
                        bfs(r, c, d, visited);
                        cnt++;
                    }
                }

                answer = Math.max(answer, cnt);
            }

            sb.append("#").append(t+1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(int sR, int sC, int day, boolean[][] visited) {
        int[] dr = new int[] {1, -1, 0, 0};
        int[] dc = new int[] {0, 0, 1, -1};

        ArrayDeque<int[]> queue = new ArrayDeque<>();

        queue.addLast(new int[] {sR, sC});
        visited[sR][sC] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            int cR = cur[0];
            int cC = cur[1];

            for (int i = 0; i < 4; i++) {
                int nR = cR + dr[i];
                int nC = cC + dc[i];

                if (nR < 0 || nR >= N || nC < 0 || nC >= N) continue;
                if (visited[nR][nC]) continue;
                if (cheese[nR][nC] <= day) continue;

                visited[nR][nC] = true;
                queue.addLast(new int[] {nR, nC});
            }
        }
    }
}
