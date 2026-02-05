import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            int answer = 0;
            int m = N / 2;
            for (int r = 0; r < N; r++) {
                String[] row = br.readLine().split("");
                for (int c = 0; c < N; c++) {
                    int v = Integer.parseInt(row[c]);
                    int n = Math.abs(r - m);
                    if (c >= n && c < N - n) answer += v;
                }
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
