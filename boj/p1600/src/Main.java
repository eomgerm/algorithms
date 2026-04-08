import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 1600 말이 되고픈 원숭이
  https://www.acmicpc.net/problem/1600
*/

public class Main {

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static int[] hdr = {-1, -1, -2, -2, 1, 2, 2, 1};
    static int[] hdc = {-2, 2, -1, 1, -2, -1, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.

        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][][] visited = new boolean[N][M][K + 1];
        Deque<int[]> queue = new ArrayDeque<>();

        queue.add(new int[] {0, 0, 0, K});
        visited[0][0][K] = true;

        int answer = -1;
        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            int cR = cur[0];
            int cC = cur[1];
            int cM = cur[2];
            int cK = cur[3];

            if (cR == N - 1 && cC == M - 1) {
                answer = cM;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nR = cR + dr[i];
                int nC = cC + dc[i];

                if (nR < 0 || nR >= N || nC < 0 || nC >= M) continue;
                if (map[nR][nC] == 1) continue;
                if (visited[nR][nC][cK]) continue;

                queue.addLast(new int[] {nR, nC, cM + 1, cK});
                visited[nR][nC][cK] = true;
            }

            if (cK > 0) {
                for (int i = 0; i < 8; i++) {
                    int nR = cR + hdr[i];
                    int nC = cC + hdc[i];

                    if (nR < 0 || nR >= N || nC < 0 || nC >= M) continue;
                    if (map[nR][nC] == 1) continue;
                    if (visited[nR][nC][cK - 1]) continue;

                    queue.addLast(new int[] {nR, nC, cM + 1, cK - 1});
                    visited[nR][nC][cK - 1] = true;
                }
            }
        }

        System.out.println(answer);
    }
}