import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] price = new int[41];
        for (int i = 1; i <= 40; i++) {
            price[i] = i * i + (i - 1) * (i - 1);
        }


        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            List<int[]> houses = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    int v = Integer.parseInt(st.nextToken());

                    if (v == 1) {
                        houses.add(new int[] {r, c});
                    }
                }
            }

            int maxK = 2 * (N - 1);
            int answer = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int[] dist = new int[maxK + 1];
                    for (int[] house : houses) {
                        int d = Math.abs(r - house[0]) + Math.abs(c - house[1]);
                        dist[d]++;
                    }

                    int totalHouses = 0;
                    for (int k = 1; k <= maxK; k++) {
                        totalHouses += dist[k - 1];

                        int income = totalHouses * M - price[k];
                        if (income >= 0) {
                            answer = Math.max(answer, totalHouses);
                        }
                    }
                }
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
