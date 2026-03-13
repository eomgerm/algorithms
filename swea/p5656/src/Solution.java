import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, R, C;
    static int[][] grid;
    static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            grid = new int[R][C];
            for (int r = 0; r < R; r++) {
                st  = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            answer = Integer.MAX_VALUE;
            solve(0, grid);

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void solve(int cnt, int[][] grid) {
        if (cnt == N) {
            int left = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (grid[r][c] != 0) left++;
                }
            }

            answer = Math.min(answer, left);
            return;
        }

        // 현재 상태 저장
        int[][] cp = new int[R][C];
        for (int r = 0; r < R; r++) {
            cp[r] = grid[r].clone();
        }

        for (int i = 0; i < C; i++) {
            // 시뮬레이션 시작
            simulate(i, grid);
            // 다음 상태로 이동
            solve(cnt + 1, grid);
            // 변경된 상태 복원
            for (int r = 0; r < R; r++) {
                grid[r] = cp[r].clone();
            }
        }
    }

    static void simulate(int c, int[][] sim) {
        // 구슬 떨어트리기
        for (int r = 0; r < R; r++) {
            if (sim[r][c] == 0) continue;
            // 연쇄 작용 시작
            chain(r, c, sim);
            // 첫 타겟을 만나면 반복문 탈출
            break;
        }

        // 3. gravity
        for (int col = 0; col < C; col++) {
            int w = R - 1;

            for (int r = R - 1; r >= 0; r--) {
                if (sim[r][col] == 0) continue;
                int tmp = sim[r][col];
                sim[r][col] = 0;
                sim[w][col] = tmp;
                w -= 1;
            }
        }
    }

    static void chain(int r, int c, int[][] sim) {
        int limit = sim[r][c] - 1;

        sim[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int nR = r;
            int nC = c;
            for (int cnt = 0; cnt < limit; cnt++) {
                nR += dr[i];
                nC += dc[i];

                if (nR < 0 || nR >= R || nC < 0 || nC >= C) break;
                if (sim[nR][nC] == 0) continue;
                chain(nR, nC, sim);
            }
        }
    }
}
