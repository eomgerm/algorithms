import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SolutionKruskal {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            parents = new int[V + 1];
            for (int i = 1; i <= V; i++) {
                parents[i] = i;
            }

            int[][] edges = new int[E][3];

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
                edges[i][2] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(edges, Comparator.comparingInt(v -> v[2]));

            long mst = 0;
            for (int[] e : edges) {
                if (find(e[0]) != find(e[1])) {
                    mst += e[2];
                    union(e[0], e[1]);
                }
            }

            sb.append("#").append(t + 1).append(" ").append(mst).append("\n");
        }

        System.out.println(sb);
    }

    static int find(int n) {
        if (parents[n] != n) {
            parents[n] = find(parents[n]);
        }

        return parents[n];
    }

    static void union(int x, int y) {
        int xR = find(x);
        int yR = find(y);

        parents[xR] = yR;
    }
}
