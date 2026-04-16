import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2098 외판원 순회
  https://www.acmicpc.net/problem/2098
*/

public class Main {

    static int N;
    static int[][] W;
    static int[][] dp;

    static int INF = 20 * 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.

        N = Integer.parseInt(br.readLine());
        W = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(tsp(0, 1));
    }

    static int tsp(int current, int mask) {
        if (mask == ((1 << N) - 1)) {
            return W[current][0] == 0 ? INF : W[current][0];
        }

        if (dp[current][mask] != -1) {
            return dp[current][mask];
        }

        int min = INF;
        for (int i = 0; i < N; i++) {
            if (W[current][i] == 0 || (mask & (1 << i)) != 0) {
                continue;
            }

            int cost = W[current][i] + tsp(i, mask | 1 << i);

            min = Math.min(min, cost);
        }

        dp[current][mask] = min;

        return dp[current][mask];
    }
}