import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            Map<Integer, List<Integer>> pos = new HashMap<>();
            int[][] micro = new int[K][4];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                micro[i][0] = Integer.parseInt(st.nextToken());
                micro[i][1] = Integer.parseInt(st.nextToken());
                micro[i][2] = Integer.parseInt(st.nextToken());
                micro[i][3] = Integer.parseInt(st.nextToken()) - 1;
            }

            while (M --> 0) {
                // 1. 이동 단계
                for (int i = 0; i < K; i++) {
                    if (micro[i][2] == 0) continue;

                    micro[i][0] += dr[micro[i][3]];
                    micro[i][1] += dc[micro[i][3]];

                    // 끝에 닿았을 때
                    if (micro[i][0] == 0 || micro[i][0] == N - 1 || micro[i][1] == 0 || micro[i][1] == N - 1) {
                        micro[i][2] /= 2;
                        micro[i][3] ^= 1;
                    }

                    pos.putIfAbsent(micro[i][0] * N + micro[i][1], new ArrayList<>());
                    pos.get(micro[i][0] * N + micro[i][1]).add(i);
                }

                // 2. 병합 단게

                for (List<Integer> mIndices : pos.values()) {
                    if (mIndices.size() < 2) continue;

                    int sum = 0;
                    int max = 0;
                    int maxIdx = 0;
                    for (int idx : mIndices) {
                        int[] mi = micro[idx];

                        sum += mi[2];
                        if (mi[2] > max) {
                            max = mi[2];
                            maxIdx = idx;
                        }
                    }

                    for (int idx : mIndices) {
                        if (idx == maxIdx) continue;
                        micro[idx][2] = 0;
                    }

                    micro[maxIdx][2] = sum;
                }

                // 3. 초기화
                pos.clear();
            }

            int answer = 0;
            for (int i = 0; i < K; i++) {
                answer += micro[i][2];
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
