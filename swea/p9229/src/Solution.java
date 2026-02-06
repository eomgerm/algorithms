import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static int[] snacks;
    static int[] selected;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            snacks = new int[N];
            for (int i = 0; i < N; i++) {
                snacks[i] = Integer.parseInt(st.nextToken());
            }

            answer = -1;
            selected = new int[2];
            comb(0, 0);

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void comb(int cnt, int s) {
        if (cnt == 2) {
            int w = selected[0] + selected[1];
            if (w <= M) answer = Math.max(answer, w);
            return;
        }

        for (int i = s; i < N; i++) {
            selected[cnt] = snacks[i];
            comb(cnt + 1, i + 1);
        }
    }
}
