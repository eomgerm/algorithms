import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[] opCnt;
    static int[] nums;
    static int[] ops;
    static int min, max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            opCnt = new int[4];
            for (int i = 0; i < 4; i++) {
                opCnt[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            nums = new int[N];
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            ops = new int[N - 1];
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            perm(0);
            sb.append("#").append(t + 1).append(" ").append(max - min).append("\n");
        }

        System.out.println(sb);
    }

    static void perm(int cnt) {
        if (cnt == N - 1) {
            int result = nums[0];
            for (int i = 0; i < N - 1; i++) {
                switch (ops[i]) {
                    case 0:
                        result += nums[i + 1];
                        break;
                    case 1:
                        result -= nums[i + 1];
                        break;
                    case 2:
                        result *= nums[i + 1];
                        break;
                    case 3:
                        result /= nums[i + 1];
                        break;
                }
            }

            min = Math.min(result, min);
            max = Math.max(result, max);

            return;
        }

        for (int i = 0; i < 4; i++) {
            if (opCnt[i] == 0) continue;
            opCnt[i]--;
            ops[cnt] = i;
            perm(cnt + 1);
            opCnt[i]++;
        }
    }
}
