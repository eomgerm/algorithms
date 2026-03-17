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
  BAEKJOON 17142 연구소 3
  https://www.acmicpc.net/problem/17142
*/

public class Main {
  static int N, M;
  static int[][] map;
  static List<int[]> viruses;
  static int blanks;
  static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
  static int answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][N];
    viruses = new ArrayList<>();
    for (int r = 0; r < N; r++) {
      st = new StringTokenizer(br.readLine());
      for (int c = 0; c < N; c++) {
        map[r][c] = Integer.parseInt(st.nextToken());
        if (map[r][c] == 2) viruses.add(new int[] {r, c});
        else if (map[r][c] == 0) blanks++;
      }
    }

    answer = Integer.MAX_VALUE;
    solve(0, new int[M], 0);

    System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
  }

  static void solve(int start, int[] chosen, int cnt) {
    if (cnt == M) {
      int[][] simMap = new int[N][N];
      for (int r = 0; r < N; r++) {
        simMap[r] = map[r].clone();
      }

      Deque<int[]> queue = new ArrayDeque<>();
      for (int i : chosen) {
        int[] virus = viruses.get(i);
        queue.addLast(virus);
        simMap[virus[0]][virus[1]] = 3;
      }

      int spread = 0;
      int elapsed = 0;
      while (!queue.isEmpty()) {
        if (elapsed > answer)
          return;
        if (spread == blanks) {
          answer = elapsed;
          return;
        }

        int size = queue.size();
        for (int i = 0; i < size; i++) {
          int[] cur = queue.removeFirst();
          int cR = cur[0];
          int cC = cur[1];

          for (int j = 0; j < 4; j++) {
            int nR = cR + dr[j];
            int nC = cC + dc[j];

            if (nR < 0 || nR >= N || nC < 0 || nC >= N)
              continue;
            if (simMap[nR][nC] == 1 || simMap[nR][nC] == 3)
              continue;
            if (simMap[nR][nC] == 0)
              spread++;
            simMap[nR][nC] = 3;
            queue.addLast(new int[]{nR, nC});
          }
        }
        elapsed++;
      }

      return;
    }

    for (int i = start; i < viruses.size(); i++) {
      chosen[cnt] = i;
      solve(i + 1, chosen, cnt + 1);
    }
  }
}