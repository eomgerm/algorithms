import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] S;
    static boolean[] result;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            S = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = Integer.MAX_VALUE;
            result = new boolean[N];
            comb(0, 0);

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void comb(int cnt, int start) {
        if (cnt == N / 2) {
            int a = 0, b = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    if (result[i] && result[j]) a += S[i][j];
                    if (!result[i] && !result[j]) b += S[i][j];
                }
            }

            int diff = Math.abs(a - b);

            answer = Math.min(answer, diff);

            return;
        }

        for (int i = start; i < N; i++) {
            result[i] = true;
            comb(cnt + 1, i + 1);
            result[i] = false;
        }
    }
}
