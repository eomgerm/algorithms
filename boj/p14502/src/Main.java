import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 14502 연구소
  https://www.acmicpc.net/problem/14502
*/

public class Main {
  static int[][] map;
  static List<int[]> p;
  static int N, M;
  static int wCnt;
  static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
  static int answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.
    StringTokenizer st = new StringTokenizer(br.readLine());
    N =  Integer.parseInt(st.nextToken());
    M  = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    p = new ArrayList<>();
    for (int r = 0; r < N; r++) {
      st = new StringTokenizer(br.readLine());
      for (int c = 0; c< M; c++) {
        map[r][c] = Integer.parseInt(st.nextToken());
        if (map[r][c] == 0) {
          p.add(new int[]{r, c});
        } else if (map[r][c] == 1) {
          wCnt++;
        }
      }
    }

    answer = 0;
    solve(0, 0);
    System.out.println(answer);
  }

  static void solve(int cnt, int start) {
    if (cnt == 3) {
      boolean[][] visited = new boolean[N][M];
      int vCnt = 0;
      for (int r = 0; r < N; r++) {
        for (int c = 0; c < M; c++) {
          if (visited[r][c] || map[r][c] != 2) continue;

          Deque<int[]>  q = new ArrayDeque<>();
          q.addLast(new int[] {r, c});
          visited[r][c] = true;

          while (!q.isEmpty()) {
            int[] cur = q.removeFirst();
            int curR = cur[0];
            int curC = cur[1];
            vCnt++;

            for (int i = 0; i < 4; i++) {
              int nextR = curR + dr[i];
              int nextC = curC + dc[i];

              if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) continue;
              if (visited[nextR][nextC]) continue;
              if (map[nextR][nextC] != 0) continue;

              q.addLast(new int[] {nextR, nextC});
              visited[nextR][nextC] = true;
            }
          }
        }
      }

      answer = Math.max(answer, N * M - wCnt - 3 - vCnt);

      return;
    }

    for (int i = start; i < p.size(); i++) {
      int[] c = p.get(i);
      map[c[0]][c[1]] = 1;
      solve(cnt + 1, i + 1);
      map[c[0]][c[1]] = 0;
    }
  }
}