import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static char[][] map;
    static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            Deque<int[]> devils = new ArrayDeque<>();
            Deque<int[]> queue = new ArrayDeque<>();
            for (int r = 0; r < N; r++) {
                map[r] = br.readLine().toCharArray();
                for (int c = 0; c < M; c++) {
                    if (map[r][c] == '*') {
                        devils.addLast(new int[] {r ,c});
                    } else if (map[r][c] == 'S') {
                        queue.addLast(new int[] {r, c, 0});
                    }
                }
            }

            int answer = bfs(devils, queue);
            sb.append("#").append(t + 1).append(" ").append(answer >= 0 ? answer : "GAME OVER").append("\n");
        }

        System.out.println(sb);
    }

    static int bfs(Deque<int[]> devils, Deque<int[]> queue) {
        while (!queue.isEmpty()) {
            int size = devils.size();
            for (int i = 0; i < size; i++) {
                int[] cur = devils.removeFirst();
                int cR = cur[0];
                int cC = cur[1];

                for (int j = 0; j < 4; j++) {
                    int nR = cR + dr[j];
                    int nC = cC + dc[j];

                    if (nR < 0 || nR >= N || nC < 0 || nC >= M) continue;
                    if (map[nR][nC] == '.' || map[nR][nC] == 'S') {
                        map[nR][nC] = '*';
                        devils.addLast(new int[] {nR, nC});
                    }
                }
            }

            size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.removeFirst();
                int cR = cur[0];
                int cC = cur[1];

                for (int j = 0; j < 4; j++) {
                    int nR = cR + dr[j];
                    int nC = cC + dc[j];

                    if (nR < 0 || nR >= N || nC < 0 || nC >= M) continue;
                    if (map[nR][nC] == 'D') {
                        return cur[2] + 1;
                    }
                    if (map[nR][nC] == '.') {
                        map[nR][nC] = 'S';
                        queue.addLast(new int[] {nR, nC, cur[2] + 1});
                    }
                }
            }
        }

        return -1;
    }
}
