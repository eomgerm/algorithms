import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class SolutionKruskal {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            double[][] islands = new double[N][2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                islands[i][0] = Double.parseDouble(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                islands[i][1] = Double.parseDouble(st.nextToken());
            }


            parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }

            double E = Double.parseDouble(br.readLine());

            List<double[]> edges = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) {
                    double x1 = islands[i][0];
                    double y1 = islands[i][1];
                    double x2 = islands[j][0];
                    double y2 = islands[j][1];

                    double L = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 -y2, 2));

                    edges.add(new double[] {i, j, E * L * L});
                }
            }

            edges.sort(Comparator.comparingDouble(v -> v[2]));

            double mst = 0;
            for (double[] e : edges) {
                if (find((int) e[0]) != find((int) e[1])) {
                    mst += e[2];
                    union((int) e[0], (int) e[1]);
                }
            }

            sb.append("#").append(t + 1).append(" ").append(Math.round(mst)).append("\n");
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
