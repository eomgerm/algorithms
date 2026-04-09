import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int D, W, K;
    static int[][] film;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // TODO: 풀이를 작성하세요.

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];
            for (int r = 0; r < D; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < W; c++) {
                    film[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            answer = Integer.MAX_VALUE;
            solve(0, 0);


            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void solve(int r, int cnt) {
        if (check()) {
            if (cnt < answer) answer = cnt;
            return;
        }

        if (r == D) return;

        int[] backup = film[r].clone();

        Arrays.fill(film[r], 0);
        solve(r + 1, cnt + 1);
        film[r] = backup.clone();

        Arrays.fill(film[r], 1);
        solve(r + 1, cnt + 1);
        film[r] = backup.clone();

        solve(r + 1 , cnt);
    }

    static boolean check() {
        for (int c = 0; c < W; c++) {
            int cnt = 1;
            int prev = film[0][c];

            for (int r = 1; r < D; r++) {
                if (prev == film[r][c]) {
                    cnt++;
                } else {
                    cnt = 1;
                    prev = film[r][c];
                }

                if (cnt >= K) break;
            }
            if (cnt < K) return false;
        }

        return true;
    }
}
