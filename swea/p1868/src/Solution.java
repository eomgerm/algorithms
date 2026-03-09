import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            int [][] map = new int[N][N];
            for (int r = 0; r < N; r++) {
                char[] row = br.readLine().toCharArray();
                for (int c = 0; c < N; c++) {
                    if (row[c] == '*') {
                        map[r][c] = -1;
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                int nR = r + dr[i];
                                int nC = c + dc[j];

                                if (nR < 0 || nR >= N || nC < 0 || nC >= N) continue;
                                if (map[nR][nC] == -1) continue;
                                map[nR][nC]++;
                            }
                        }
                    }
                }
            }

            boolean[][] v = new boolean[N][N];
            Deque<int[]> queue = new ArrayDeque<>();

            int answer = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (v[r][c] || map[r][c] == -1 || map[r][c] > 0) continue;

                    answer++;
                    queue.addLast(new int[] {r, c});
                    v[r][c] = true;

                    while (!queue.isEmpty()) {
                        int[] cur = queue.removeFirst();
                        int cR = cur[0];
                        int cC = cur[1];


                        if (map[cR][cC] > 0) continue;

                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                int nR = cR + dr[i];
                                int nC = cC + dc[j];

                                if (nR < 0 || nR >= N || nC < 0 || nC >= N) continue;
                                if (v[nR][nC] || map[nR][nC] == -1) continue;

                                v[nR][nC] = true;
                                queue.addLast(new int[] {nR, nC});
                            }
                        }
                    }
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] > 0 && !v[r][c]) answer++;
                }
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
