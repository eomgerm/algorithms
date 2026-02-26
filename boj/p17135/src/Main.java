import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17135 캐슬 디펜스
  https://www.acmicpc.net/problem/17135
*/

public class Main {
  static int N, M, D;
  static int[][] map;
  static int enemies;
  static int[] archers;
  static int[] dr = {1, 0, -1, 0}, dc = {0, -1, 0, 1};
  static int answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); D = Integer.parseInt(st.nextToken());

    map = new int[N + 1][M];
    for (int r = 0; r < N; r++) {
      st = new StringTokenizer(br.readLine());
      for (int c = 0; c < M; c++) {
        map[r][c] = Integer.parseInt(st.nextToken());
        if (map[r][c] == 1) enemies++;
      }
    }
    Arrays.fill(map[N], 2);

    answer = 0;
    archers = new int[3];
    solve(0, 0);

    System.out.println(answer);
  }

  static void solve (int cnt, int start) {
    if (cnt == 3) {

      // init
      int eCnt = enemies, kCnt = 0;
      int[][] targets = new int[3][2];

      int[][] simMap= new int[N + 1][M];
      for (int r = 0; r < N + 1; r++) {
        simMap[r] = map[r].clone();
      }

      // play
      while (eCnt > 0) {
        // aim
        for (int i = 0; i < 3; i++) {
          targets[i] = aim(N, archers[i], simMap);
        }

        // fire
        for (int i = 0; i < 3; i++) {
          int[] target = targets[i];
          if (target == null) continue;
          if (simMap[target[0]][target[1]] == 1) {
            eCnt--;
            kCnt++;
            simMap[target[0]][target[1]] = 0;
          }
        }

        // move
        for (int r = N - 1; r >= 0; r--) {
          for (int c = 0; c < M; c++) {
            if (r == N - 1 && simMap[r][c] == 1) {
              eCnt--;
            } else {
              simMap[r + 1][c] = simMap[r][c];
            }
          }
        }
        Arrays.fill(simMap[0], 0);
      }

      answer = Math.max(answer, kCnt);

      return;
    }

    for (int i = start; i < M; i++) {
      archers[cnt] = i;
      solve(cnt + 1, i + 1);
    }
  }

  static int[] aim(int sR, int sC, int[][] map) {
    boolean[][] visited = new boolean[N + 1][M];
    Deque<int[]> queue = new ArrayDeque<>();

    visited[sR][sC] = true;
    queue.addLast(new int[]{sR, sC, 0});

    List<int[]> cand = new ArrayList<>();

    while (!queue.isEmpty()) {
      int[] cur = queue.removeFirst();
      int cR = cur[0];
      int cC = cur[1];
      int cD = cur[2];

      if (map[cR][cC] == 1) {
        cand.add(cur);
        continue;
      }

      for (int i = 0; i < 4; i++) {
        int nR = cR + dr[i];
        int nC = cC + dc[i];
        int nD = cD + 1;

        if (nR < 0 || nR >= N || nC < 0 || nC >= M) continue;
        if (visited[nR][nC]) continue;
        if (nD > D) continue;

        visited[nR][nC] = true;
        queue.addLast(new int[]{nR, nC, nD});
      }
    }

    cand.sort(Comparator.<int[]>comparingInt(v -> v[2]).thenComparingInt(v -> v[1]));

    return cand.isEmpty() ? null : cand.get(0);
  }
}