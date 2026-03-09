import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

            init(n);

            sb.append("#").append(t + 1).append(" ");
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int op = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                if (op == 0) {
                    union(x, y);
                } else if (op == 1) {
                    sb.append(find(x) == find(y) ? 1 : 0);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void init(int N) {
        nodes = new int[N + 1][2];
        for(int i = 1; i < N + 1; i++) {
            nodes[i] = new int[] {i, 0};
        }
    }

    static void union(int x, int y) {
        int xR = find(x);
        int yR = find(y);

        if (nodes[xR][1] < nodes[yR][1]) {
            nodes[xR][0] = yR;
        } else if (nodes[xR][1] > nodes[yR][1]) {
            nodes[yR][0] = xR;
        } else {
            nodes[xR][0] = yR;
            nodes[yR][1]++;
        }
    }

    static int find(int n) {
        if (nodes[n][0] != n) {
            nodes[n][0] = find(nodes[n][0]);
        }

        return nodes[n][0];
    }
}
