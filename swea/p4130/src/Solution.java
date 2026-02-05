import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());

            List<Integer>[] mags = new ArrayList[5];

            for (int i = 1; i <= 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                mags[i] = new ArrayList<>();
                for (int j = 0; j < 8; j++) {
                    mags[i].add(Integer.parseInt(st.nextToken()));
                }
            }

            for (int k = 0; k < K; k++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int target = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                int[] rotate = new int[5];
                rotate[target] = dir;

                for (int r = target - 1; r > 0; r--) {
                    int l = r + 1;

                    int ld = rotate[l];
                    if (ld == 0) continue;
                    if (mags[r].get(2) != mags[l].get(6)) {
                        rotate[r] = -ld;
                    }
                }

                for (int l = target + 1; l < 5; l++) {
                    int r = l - 1;

                    int rd = rotate[r];
                    if (rd == 0) continue;
                    if (mags[r].get(2) != mags[l].get(6)) {
                        rotate[l] = -rd;
                    }
                }

                for (int i = 1; i < 5; i++) {
                    Collections.rotate(mags[i], rotate[i]);
                }
            }

            int answer = 0;
            for (int i = 1; i < 5; i++) {
                answer += mags[i].get(0) == 0 ? 0 : (int) Math.pow(2, i - 1);
            }

            sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
