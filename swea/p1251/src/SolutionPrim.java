import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SolutionPrim {
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

            double E = Double.parseDouble(br.readLine());

            List<double[]>[] graph = new List[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) {
                    double x1 = islands[i][0];
                    double y1 = islands[i][1];
                    double x2 = islands[j][0];
                    double y2 = islands[j][1];

                    double L = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 -y2, 2));

                    graph[i].add(new double[] {j, E * L * L});
                    graph[j].add(new double[] {i, E * L * L});
                }
            }

            boolean[] v = new boolean[N];
            PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e[1]));
            pq.add(new double[] {1, 0});
            double mst = 0;

            while (!pq.isEmpty()) {
                double[] cur = pq.poll();
                if (v[(int) cur[0]]) continue;
                v[(int) cur[0]] = true;
                mst += cur[1];

                for (double[] e : graph[(int) cur[0]]) {
                    if (v[(int) e[0]]) continue;
                    pq.add(e);
                }
            }

            sb.append("#").append(t + 1).append(" ").append(Math.round(mst)).append("\n");
        }

        System.out.println(sb);
    }
}
