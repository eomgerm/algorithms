import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            long N = Long.parseLong(br.readLine());
            long answer = 0;

            while (N != 2) {
                if (Math.sqrt(N) == (long) Math.sqrt(N)) {
                    N = (long) Math.sqrt(N);
                    answer++;
                }
                else {
                    long n = (long) Math.sqrt(N) + 1;
                    answer += n * n - N;
                    N = n * n;
                }
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
