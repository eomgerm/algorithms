import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17822 원판 돌리기
  https://www.acmicpc.net/problem/17822
*/

public class Main {

    static int[] di = {1, -1, 0, 0}, dj = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        List<Integer>[] disc = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            disc[i] = new ArrayList<>();
        }

        int sum = 0;
        int count = N * M;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                sum += n;
                disc[i].add(n);
            }
        }

        int[][] rOps = new int[T][3];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            rOps[i][0] = Integer.parseInt(st.nextToken());
            rOps[i][1] = Integer.parseInt(st.nextToken());
            rOps[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int[] op : rOps) {

            int x = op[0];
            int d = op[1];
            int k = op[2];

            for (int i = x; i <= N; i += x) {
                Collections.rotate(disc[i], d == 0 ? k : -k);
            }

            boolean deleted = false;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (disc[i].get(j) == 0) {
                        continue;
                    }

                    boolean dCur = false;
                    Deque<int[]> queue = new ArrayDeque<>();
                    queue.addLast(new int[]{i, j});
                    boolean[][] v = new boolean[N + 1][M];
                    v[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] cur = queue.removeFirst();
                        int ci = cur[0];
                        int cj = cur[1];
                        int cn = disc[ci].get(cj);

                        for (int m = 0; m < 4; m++) {
                            int ni = ci + di[m];
                            int nj = ((cj + dj[m] + M) % M);

                            if (ni < 1 || ni > N) {
                                continue;
                            }
                            if (v[ni][nj]) {
                                continue;
                            }

                            int nn = disc[ni].get(nj);
                            if (cn == nn) {
                                deleted = true;
                                dCur = true;
                                queue.addLast(new int[]{ni, nj});
                                v[ni][nj] = true;
                            }
                        }
                        if (dCur) {
                            count--;
                            sum -= cn;
                            disc[ci].set(cj, 0);
                        }
                    }
                }
            }

            if (sum == 0) {
                break;
            }

            if (!deleted) {
                double mean = sum / (double) count;

                for (int i = 1; i <= N; i++) {
                    for (int j = 0; j < M; j++) {
                        int n = disc[i].get(j);
                        if (n == 0) {
                            continue;
                        }

                        if (n < mean) {
                            disc[i].set(j, n + 1);
                            sum++;
                        } else if (n > mean) {
                            disc[i].set(j, n - 1);
                            sum--;
                        }
                    }
                }
            }
        }

        System.out.println(sum);
    }
}