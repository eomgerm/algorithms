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
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            String[] nD = new String[N];

            int half = N % 2 == 0 ? (N / 2) : (N / 2  + 1);
            for (int i = 0; i < N; i++) {
                if (i < half) {
                    nD[i * 2] = st.nextToken();
                } else {
                    nD[(i - half) * 2 + 1] = st.nextToken();
                }
            }

            sb.append("#").append(t + 1).append(" ");
            for (String s : nD) {
                sb.append(s).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
