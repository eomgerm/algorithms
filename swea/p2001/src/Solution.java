import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] prefixSum = new int[N + 1][N + 1];
            for (int r = 1; r < N + 1; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 1; c < N + 1; c++) {
                    prefixSum[r][c] = prefixSum[r - 1][c] + prefixSum[r][c - 1] - prefixSum[r - 1][c - 1] + Integer.parseInt(st.nextToken());
                }
            }

            int answer = Integer.MIN_VALUE;
            for (int r = 1; r <= N - M + 1; r++) {
                for (int c = 1; c <= N - M + 1; c++) {
                    int r2 = r + M - 1;
                    int c2 = c + M - 1;
                    answer = Math.max(prefixSum[r2][c2] - prefixSum[r2][c - 1] - prefixSum[r - 1][c2] + prefixSum[r - 1][c - 1], answer);
                }
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
