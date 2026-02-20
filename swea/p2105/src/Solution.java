import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[][] map;
    static int[] dr = {1, 1, -1, -1},
        dc = {1, -1, -1, 1};
    static int answer;
    static boolean found;
    static Set<Integer> d;
    static int startR, startC;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            found = false;
            answer = 0;

            for (int r = 0; r < N - 2; r++) {
                for (int c = 1; c < N - 1; c++) {
                    d = new HashSet<>();
                    startR = r;
                    startC = c;
                    dfs(r, c, 0);
                }
            }

            sb.append("#").append(t + 1).append(" ").append(found ? answer : -1).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int curR, int curC, int seq) {
        d.add(map[curR][curC]);

        for (int i = seq; i <= Math.min(seq + 1, 3); i++) {
            int nextR = curR + dr[i];
            int nextC = curC + dc[i];

            if (i == 3 && nextR == startR && nextC == startC) {
                found = true;
                answer = Math.max(d.size(), answer);
                break;
            }

            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) continue;
            if (d.contains(map[nextR][nextC])) continue;
            dfs(nextR, nextC, i);
        }

        d.remove(map[curR][curC]);
    }
}
