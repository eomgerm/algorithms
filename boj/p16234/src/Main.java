import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 16234 인구 이동
  https://www.acmicpc.net/problem/16234
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 코드를 작성하세요.
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());

    int[][] map = new int[N][N];

    for (int r = 0; r < N; r++) {
      st = new StringTokenizer(br.readLine());
      for (int c = 0; c < N; c++) {
        map[r][c] = Integer.parseInt(st.nextToken());
      }
    }

    int answer = 0;
    int[] dr = new int[] {1, -1, 0, 0};
    int[] dc = new int[] {0, 0, 1, -1};
    while (true) {
      ArrayDeque<int[]> queue = new ArrayDeque<>();
      boolean[][] visited = new boolean[N][N];

      boolean moved = false;
      for (int r = 0; r < N; r++) {
        for (int c = 0; c < N; c++) {
          if (visited[r][c]) continue;

          queue.add(new int[] {r, c});
          Set<Integer> union = new HashSet<>();
          union.add(r * N + c);

          while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();

            int cR = cur[0];
            int cC = cur[1];
            for (int i = 0; i < 4; i++) {
              int nR = cR + dr[i];
              int nC = cC + dc[i];

              if (nR < 0 || nR >= N || nC < 0 || nC >= N) continue;
              if (visited[nR][nC]) continue;

              int diff = Math.abs(map[cR][cC] - map[nR][nC]);
              if (diff < L || diff > R) continue;

              visited[nR][nC] = true;
              queue.add(new int[] {nR, nC});
              union.add(nR * N + nC);
              moved = true;
            }
          }

          if (union.size() > 1) {
            int sum = union.stream().mapToInt(v -> map[v / N][v % N]).sum();
            int p = sum / union.size();
            for (int v : union) {
              map[v / N][v % N] = p;
            }
          }
        }
      }


      if (!moved) {
        break;
      }

      answer++;
    }

    System.out.println(answer);
  }
}