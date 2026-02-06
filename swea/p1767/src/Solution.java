import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] cells;
    static List<int[]> cores;
    static int maxCores;
    static int answer;

    static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            int connected = 0;
            cells = new int[N][N];
            cores = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    cells[r][c] = Integer.parseInt(st.nextToken());
                    if (cells[r][c] == 1) {
                        if (r == 0 || r == N - 1 || c == 0 || c == N - 1) connected++;
                        else cores.add(new int[] {r, c});
                    }
                }
            }

            answer = Integer.MAX_VALUE;
            maxCores = 0;

            dfs(0, 0, connected);

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int cnt, int length, int cCores) {
        if (cnt == cores.size()) {
            if (cCores > maxCores) {
                maxCores = cCores;
                answer = length;
            } else if (cCores == maxCores) {
                answer = Math.min(answer, length);
            }

            return;
        }

        int[] cCore = cores.get(cnt);
        int cR = cCore[0];
        int cC = cCore[1];

        for (int i = 0; i < 4; i++) {
            int nR = cR + dr[i];
            int nC = cC + dc[i];
            int len = 0;

            while (nR >= 0 && nR < N && nC >= 0 && nC < N) {

                if (cells[nR][nC] != 0) {
                    len = 0;
                    break;
                }
                len++;
                nR += dr[i];
                nC += dc[i];
            }

            if (len > 0) {
                nR = cR + dr[i];
                nC = cC + dc[i];
                while (nR >= 0 && nR < N && nC >= 0 && nC < N) {
                    cells[nR][nC] = 2;
                    nR += dr[i];
                    nC += dc[i];
                }

                dfs(cnt + 1, length + len, cCores + 1);

                nR = cR + dr[i];
                nC = cC + dc[i];
                while (nR >= 0 && nR < N && nC >= 0 && nC < N) {
                    cells[nR][nC] = 0;
                    nR += dr[i];
                    nC += dc[i];
                }
            }
        }

        dfs(cnt + 1, length, cCores);
    }
}
