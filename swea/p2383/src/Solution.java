import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            List<int[]> people = new ArrayList<>();
            int[][] stairs = new int[2][3];
            int sIdx = 0;
            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    int v = Integer.parseInt(st.nextToken());

                    if (v == 1) {
                        people.add(new int[]{r, c});
                    } else if (v >= 2) {
                        stairs[sIdx++] = new int[]{r, c, v};
                    }
                }
            }

            int P = people.size();
            int answer = Integer.MAX_VALUE;
            for (int i = 0; i < (1 << P); i++) {
                List<Integer> t1 = new ArrayList<>();
                List<Integer> t2 = new ArrayList<>();
                for (int j = 0; j < P; j++) {
                    int[] person = people.get(j);
                    int pR = person[0];
                    int pC = person[1];
                    if ((i & (1 << j)) != 0) {
                        int[] stair = stairs[0];
                        int sR = stair[0];
                        int sC = stair[1];

                        t1.add(Math.abs(sR - pR) + Math.abs(sC - pC));
                    } else {
                        int[] stair = stairs[1];
                        int sR = stair[0];
                        int sC = stair[1];

                        t2.add(Math.abs(sR - pR) + Math.abs(sC - pC));
                    }
                }
                int g1 = t1.size();
                int g2 = t2.size();
                t1.sort(Comparator.naturalOrder());
                t2.sort(Comparator.naturalOrder());

                int[] g1F = new int[g1];
                for (int k = 0; k < g1; k++) {
                    int arrival = t1.get(k);

                    if (k < 3) {
                        g1F[k] = arrival + 1 + stairs[0][2];
                    } else {
                        int start = Math.max(arrival + 1, g1F[k - 3]);

                        g1F[k] = start + stairs[0][2];
                    }
                }

                int[] g2F = new int[g2];
                for (int k = 0; k < g2; k++) {
                    int arrival = t2.get(k);

                    if (k < 3) {
                        g2F[k] = arrival + 1 + stairs[1][2];
                    } else {
                        int start = Math.max(arrival + 1, g2F[k - 3]);

                        g2F[k] = start + stairs[1][2];
                    }
                }

                int time1 = g1 == 0 ? 0 : g1F[g1 - 1];
                int time2 = g2 == 0 ? 0 : g2F[g2 - 1];

                answer = Math.min(answer, Math.max(time1, time2));
            }
            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
