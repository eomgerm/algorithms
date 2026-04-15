import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] points;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // TODO: 풀이를 작성하세요.
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            points = new int[N][2];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                points[i][0] = Integer.parseInt(st.nextToken());
                points[i][1] = Integer.parseInt(st.nextToken());
            }

            answer = Long.MAX_VALUE;
            comb(0, 0, new boolean[N]);

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void comb(int cnt, int start, boolean[] v) {
        if (cnt == N / 2) {
            long x = 0;
            long y = 0;

            for (int i = 0; i < N; i++) {
                if (v[i]) {
                    x += points[i][0];
                    y += points[i][1];
                } else {
                    x -= points[i][0];
                    y -= points[i][1];
                }
            }

            answer = Math.min(answer, x * x + y * y);

            return;
        }

        for (int i = start; i < N; i++) {
            v[i] = true;
            comb(cnt + 1, i + 1, v);
            v[i] = false;
        }
    }
}
