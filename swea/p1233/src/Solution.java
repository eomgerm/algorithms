import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static char[] nodes;
    static int[][] tree;
    static int answer = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < 10; t++) {
            int N = Integer.parseInt(br.readLine());

            answer = 1;
            nodes = new char[N + 1];
            tree = new int[N + 1][];
            for (int i = 1; i < N + 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int node = Integer.parseInt(st.nextToken());
                String v = st.nextToken();
                nodes[node] = v.charAt(0);

                int l = Integer.parseInt(st.hasMoreTokens() ? st.nextToken() : "0");
                int r = Integer.parseInt(st.hasMoreTokens() ? st.nextToken() : "0");

                if (l == 0 && r == 0) {
                    tree[node] = null;
                } else {
                    tree[node] = new int[] {l, r};
                }
            }

            traverse(1);
            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static void traverse(int node) {
        if (answer == 0) return;

        if (tree[node] == null) {
            if (!Character.isDigit(nodes[node])) {
                answer = 0;
            }
            return;
        }

        int l = tree[node][0];
        int r = tree[node][1];

        if (l == 0 || r == 0) {
            answer = 0;
            return;
        }

        traverse(tree[node][0]);
        traverse(tree[node][1]);
        nodes[node] = '1';
    }
}
