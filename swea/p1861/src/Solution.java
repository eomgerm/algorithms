import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] rooms = new int[N][N];

            for (int  i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    rooms[i][j] = Integer.parseInt(st.nextToken());
                }
            }



            int[] dr = {1, -1, 0, 0};
            int[] dc = {0, 0, 1, -1};
            int aStart = N + 1;
            int aCnt = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int start = rooms[r][c];
                    int cnt = 0;
                    ArrayDeque<int[]> queue = new ArrayDeque<>();
                    boolean[][] v = new boolean[N][N];
                    queue.addLast(new int[] {r, c});
                    v[r][c] = true;
                    while (!queue.isEmpty()) {
                        int[] cur = queue.removeFirst();
                        int curR = cur[0];
                        int curC = cur[1];
                        cnt++;
                        for (int i = 0; i < 4; i++) {
                            int nextR = curR + dr[i];
                            int nextC = curC + dc[i];

                            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N)
                                continue;
                            if (v[nextR][nextC])
                                continue;
                            if (rooms[curR][curC] + 1 != rooms[nextR][nextC])
                                continue;
                            v[nextR][nextC] = true;
                            queue.addLast(new int[]{nextR, nextC});
                            break;
                        }
                    }
                    if (aCnt < cnt) {
                        aCnt = cnt;
                        aStart = start;
                    } else if (aCnt == cnt) {
                        aStart = Math.min(aStart, start);
                    }
                }
            }

            sb.append("#").append(t + 1).append(" ").append(aStart).append(" ").append(aCnt).append("\n");
        }

        System.out.println(sb);
    }
}
