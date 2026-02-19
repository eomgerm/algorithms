import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int[][] bcs;
    static int A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            int[] pa = new int[M], pb = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                pa[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                pb[i] = Integer.parseInt(st.nextToken());
            }

            bcs = new int[A][4];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    bcs[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] dx = {0, 0, 1, 0, -1};
            int[] dy = {0, -1, 0, 1, 0};

            int[] a = {1, 1}, b = {10, 10};
            int answer = charge(a, b);
            for (int i = 0; i < M; i++) {
                a[0] += dx[pa[i]];
                a[1] += dy[pa[i]];
                b[0] += dx[pb[i]];
                b[1] += dy[pb[i]];

                answer += charge(a, b);
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static int charge(int[] a, int[] b) {
        List<Integer> bca = new ArrayList<>();
        List<Integer> bcb = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            int[] bc = bcs[i];
            int da = Math.abs(a[0] - bc[0]) + Math.abs(a[1] - bc[1]);
            int db = Math.abs(b[0] - bc[0]) + Math.abs(b[1] - bc[1]);

            if (da <= bc[2]) {
                bca.add(i);
            }
            if (db <= bc[2]) {
                bcb.add(i);
            }
        }

        int max = 0;
        if (bca.size() > bcb.size()) {
            for (int i = 0; i < bca.size() - bcb.size(); i++) {
                bcb.add(-1);
            }
        } else {
            for (int i = 0; i < bcb.size() - bca.size(); i++) {
                bca.add(-1);
            }
        }

        for (int i : bca) {
            for (int j : bcb) {
                int sum;

                if (i == -1) {
                    sum = bcs[j][3];
                } else if (j == -1) {
                    sum = bcs[i][3];
                } else {
                    if (i == j) sum = bcs[i][3];
                    else sum = bcs[i][3] + bcs[j][3];
                }

                max = Math.max(sum, max);
            }
        }

        return max;
    }
}