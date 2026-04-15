import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            sb.append("#").append(t + 1).append(" ");
            String S = br.readLine();
            int len = S.length();

            if (len == 0) {
                sb.append("0").append("\n");
                continue;
            }

            int[] pi = new int[len];
            int j = 0; // abcabc
            for (int i = 1; i < len; i++) {
                while (j > 0 && S.charAt(i) != S.charAt(j)) {
                    j = pi[j - 1];
                }

                if (S.charAt(i) == S.charAt(j)) {
                    pi[i] = ++j;
                }
            }

            int p = len - pi[len - 1];
            if (len % p == 0) {
                sb.append(len / p);
            } else {
                sb.append(1);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
