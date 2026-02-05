import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] a, b;
    static boolean[] v;
    static int win, lose;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = new boolean[19];
            a = new int[9];
            b = new int[9];
            win = lose = 0;

            for (int i = 0; i < 9; i++) {
                int n = Integer.parseInt(st.nextToken());
                v[n] = true;
                a[i] = n;
            }

            perm(0);
            sb.append("#").append(t + 1).append(" ").append(win).append(" ").append(lose).append("\n");
        }

        System.out.println(sb);
    }

    static void perm(int cnt) {
        if (cnt == 9) {
            int aScore, bScore;
            aScore = bScore = 0;

            for (int i = 0; i < 9; i++) {
                if (a[i] > b[i]) {
                    aScore += a[i] + b[i];
                } else {
                    bScore += a[i] + b[i];
                }
            }

            if (aScore > bScore) {
                win++;
            } else {
                lose++;
            }

            return;
        }

        for (int i = 1; i < 19; i++) {
            if (v[i]) continue;
            v[i] = true;
            b[cnt] = i;
            perm(cnt + 1);
            v[i] = false;
        }
    }
}
