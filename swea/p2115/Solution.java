import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M, C, answer = 0;
    static int[][] beeHouse, profits;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            beeHouse = new int[N][N];
            profits = new int[N][N];

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    beeHouse[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 해당 칸을 시작으로 하는 구간의 꿀의 최대 수익 구하기
            for (int r = 0; r < N; r++) {
                // 부등호 주의!
                for (int c = 0; c <= N - M; c++) {
                    getProfit(0, r, c, 0, 0);
                }
            }

            // 실제로 구간을 선택해서 (조합) 두 꿀벌의 최대 수익 구하기
            solve(0, 0, 0, 0);

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
            answer = 0;
        }

        System.out.println(sb);
    }

    static void getProfit(int cnt, int r, int c, int honey, int profit) {
        if (honey > C) return;

        if (cnt == M) {
            profits[r][c - M] = Math.max(profits[r][c - M], profit);
            return;
        }

        // 해당 칸을 채취하는 경우
        getProfit(cnt + 1, r, c + 1, honey + beeHouse[r][c], profit + beeHouse[r][c] * beeHouse[r][c]);
        // 채취하지 않는 경우
        getProfit(cnt + 1, r, c + 1, honey, profit);
    }


    static void solve(int cnt, int sR, int sC, int ans) {
        if (cnt == 2) {
            answer = Math.max(answer, ans);
            return;
        }

        // 해당 칸을 시작으로 하는 구간 선택
        for (int r = sR; r < N; r++) {
            // 부등호 주의!
            for (int c = sC; c <= N - M; c++) {
                // 다음 꿀벌은 같은 행에서 구간이 겹치면 안됨
                solve(cnt + 1, r, c + M, ans + profits[r][c]);
            }
            // 다음 행에서부터는 첫 열부터 선택해도 상관없음
            sC = 0;
        }
    }
}
