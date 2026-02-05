import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution  {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int answer = 0;

            char[] bits = br.readLine().toCharArray();

            char prev = '0';
            for (char bit : bits) {
                if (bit != prev) {
                    answer++;
                    prev = bit;
                }
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
