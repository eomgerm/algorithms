import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            int[][] map = new int[N][N];
            for (int r = 0; r < N; r++) {
                char[] row = br.readLine().toCharArray();
                for (int c = 0; c < N; c++) {
                    map[r][c] = row[c] - '0';
                }
            }

            int[][] d = new int[N][N];
            for (int r = 0; r < N; r++) {
                Arrays.fill(d[r], Integer.MAX_VALUE);
            }
            d[0][0] = 0;

            int[] dr = {1, -1, 0, 0};
            int[] dc = {0, 0, 1, -1};
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[2]));
            pq.add(new int[] {0, 0, map[0][0]});
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int curR = cur[0], curC = cur[1], dist = cur[2];

                if (d[curR][curC] < dist) continue;

                for (int i = 0; i < 4; i++) {
                    int nextR = curR + dr[i];
                    int nextC = curC + dc[i];

                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) continue;
                    int c = dist + map[nextR][nextC];
                    if (c < d[nextR][nextC]) {
                        d[nextR][nextC] = c;
                        pq.add(new int[] {nextR, nextC, c});
                    }
                }
            }

            sb.append("#").append(t + 1).append(" ").append(d[N-1][N-1]).append("\n");
        }

        System.out.println(sb);
    }
}
