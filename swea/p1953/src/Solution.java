import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        boolean[][] pipes = {
            {},
            {true, true, true, true},
            {true, true, false, false},
            {false, false, true, true},
            {true, false, false, true},
            {false, true, false, true},
            {false, true, true, false},
            {true, false, true, false}
        };
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][M];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            ArrayDeque<int[]> queue = new ArrayDeque<>();
            boolean[][] v = new boolean[N][M];

            queue.addLast(new int[] {R, C, 1});
            v[R][C] = true;
            int answer = 1;
            while (!queue.isEmpty()) {
                int[] cur = queue.removeFirst();
                int curR = cur[0];
                int curC = cur[1];
                int d = cur[2];
                int curPipe = map[curR][curC];

                if (d == L) continue;

                for (int i = 0; i < 4; i++) {
                    if (!pipes[curPipe][i]) continue;
                    int nextR = curR + dr[i];
                    int nextC = curC + dc[i];

                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) continue;
                    if (v[nextR][nextC]) continue;
                    if (map[nextR][nextC] == 0) continue;
                    if (!pipes[map[nextR][nextC]][i ^ 1]) continue;

                    queue.addLast(new int[] {nextR, nextC, d + 1});
                    v[nextR][nextC] = true;
                    answer++;

                }
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
