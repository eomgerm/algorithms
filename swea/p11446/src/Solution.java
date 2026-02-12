import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            long M = Long.parseLong(st.nextToken());

            long[] candies = new long[N];
            long max = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                candies[i] = Long.parseLong(st.nextToken());
                max = Math.max(max, candies[i]);
            }

            long answer = 0;
            long s = 1, e = max;
            while (s <= e) {
                long mid = (s + e) / 2;

                long m = 0;
                for (int i = 0; i < N; i++) {
                    m += candies[i] / mid;
                }

                if (m < M) {
                    e = mid - 1;
                } else {
                    answer = mid;
                    s = mid + 1;
                }
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
