import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 1194 달이 차오른다, 가자.
  https://www.acmicpc.net/problem/1194
*/

public class Main {

    static int[] dr = new int[]{1, -1, 0, 0};
    static int[] dc = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];

        int[] start = {};
        for (int r = 0; r < N; r++) {
            char[] row = br.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                map[r][c] = row[c];
                if (row[c] == '0') {
                    start = new int[] {r, c};
                }
            }
        }

        boolean[][][] visited = new boolean[N][M][64];
        Deque<int[]> queue = new ArrayDeque<>();

        queue.addLast(new int[] {start[0], start[1], 0, 0});
        visited[start[0]][start[1]][0] = true;

        int answer = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            int cR = cur[0];
            int cC = cur[1];
            int cM = cur[2];
            int cD = cur[3];

            if (map[cR][cC] == '1') {
                answer = cD;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nR = cR + dr[i];
                int nC = cC + dc[i];

                if (nR < 0 || nR >= N || nC < 0 || nC >= M) {
                    continue;
                }

                if (visited[nR][nC][cM]) continue;

                char c = map[nR][nC];

                if (c == '#') continue;

                if (c == '.' || c == '1' || c == '0') {
                    queue.addLast(new int[] {nR, nC, cM, cD + 1});
                    visited[nR][nC][cM] = true;
                } else if (c >= 'a' && c <= 'f') {
                    int nM = cM | 1 << (c - 'a');

                    queue.add(new int[] {nR, nC, nM , cD + 1});
                    visited[nR][nC][nM] = true;
                } else if (c >= 'A' && c <= 'F' && (cM & (1 << c - 'A')) != 0) {
                    queue.add(new int[] {nR, nC, cM, cD + 1});
                    visited[nR][nC][cM] = true;
                }
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}