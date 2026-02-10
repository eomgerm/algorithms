import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int max = 0;
            int[] trees = new int[N];
            for (int i = 0; i < N; i++) {
                int h = Integer.parseInt(st.nextToken());
                max = Math.max(max, h);
                trees[i] = h;
            }

            int answer = 0;
            for (int h : trees) {
                int d = max - h;
                answer += d - d / 3;
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
