import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 16946 벽 부수고 이동하기 4
  https://www.acmicpc.net/problem/16946
*/

public class Main {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 코드를 작성하세요.

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int r = 0; r < N; r++) {
            char[] row = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                map[r][c] = row[c] - '0';
            }
        }

        Map<Integer, Integer> groupSize = new HashMap<>();
        int id = 2;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] != 0) {
                    continue;
                }

                Deque<int[]> queue = new ArrayDeque<>();

                queue.addLast(new int[] {r, c});
                map[r][c] = id;
                int cnt = 0;

                while (!queue.isEmpty()) {
                    int[] cur = queue.removeFirst();
                    int cR = cur[0];
                    int cC = cur[1];

                    cnt++;
                    for (int i = 0; i < 4; i++) {
                        int nR = cR + dr[i];
                        int nC = cC + dc[i];

                        if (nR < 0 || nR >= N || nC < 0 || nC >= M) continue;
                        if (map[nR][nC] != 0) continue;

                        queue.addLast(new int[] {nR, nC});
                        map[nR][nC] = id;
                    }
                }

                groupSize.put(id, cnt);
                id++;
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] != 1) {
                    sb.append("0");
                    continue;
                }

                Set<Integer> groups = new HashSet<>();
                for (int i = 0; i < 4; i++) {
                    int nR = r + dr[i];
                    int nC = c + dc[i];

                    if (nR < 0 || nR >= N || nC < 0 || nC >= M) continue;
                    if (map[nR][nC] >= 2) {
                        groups.add(map[nR][nC]);
                    }
                }
                int cnt = 1;
                for (int i : groups) {
                    cnt += groupSize.get(i);
                }
                sb.append(cnt % 10);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}