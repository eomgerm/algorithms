import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int answer;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int N, K;
    static int[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            int max = 0;
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, map[r][c]);
                }
            }

            List<int[]> h = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == max) {
                        h.add(new int[] {r, c});
                    }
                }
            }

            answer = 0;
            for (int[] s : h) {
                v = new boolean[N][N];
                dfs(s[0], s[1], true, 1);
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int curR, int curC, boolean available, int len) {
        v[curR][curC] = true;
        answer = Math.max(answer, len);

        for (int i = 0; i < 4; i++) {
            int nextR = curR + dr[i];
            int nextC = curC + dc[i];

            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) continue;
            if (v[nextR][nextC]) continue;
            if (available && map[nextR][nextC] >= map[curR][curC]) {
                if (map[nextR][nextC] - map[curR][curC] < K) {
                    int tmp = map[nextR][nextC];
                    map[nextR][nextC] = map[curR][curC] - 1;

                    dfs(nextR, nextC, false, len + 1);

                    map[nextR][nextC] = tmp;
                    continue;
                }
            }
            if (map[nextR][nextC] >= map[curR][curC]) continue;
            dfs(nextR, nextC, available, len + 1);
        }
        v[curR][curC] = false;
    }
}
