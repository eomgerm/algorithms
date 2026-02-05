import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 15683 감시
  https://www.acmicpc.net/problem/15683
*/

public class Main {
  static int R, C;
  static int[][] office;
  static ArrayList<int[]> cctvs = new ArrayList<>();
  static int[][][] dir = {
      {{0}, {1}, {2}, {3}},
      {{0, 2}, {1, 3}},
      {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
      {{1, 2, 3}, {0, 2, 3}, {0, 1, 3}, {0, 1, 2}},
      {{0, 1, 2, 3}}
  };
  static int answer = Integer.MAX_VALUE;


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.

    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    office = new int[R][C];

    for (int r = 0; r < R; r++) {
      st = new StringTokenizer(br.readLine());
      for (int c = 0; c < C; c++) {
        office[r][c] = Integer.parseInt(st.nextToken());
        if (office[r][c] >= 1 && office[r][c] <= 5) cctvs.add(new int[] {r ,c});
      }
    }

    solve(0, new int[cctvs.size()]);
    System.out.println(answer);
  }

  static void solve(int depth, int[] directions) {
    if (depth == cctvs.size()) {
      boolean[][] canMonitor = new boolean[R][C];

      for (int i = 0; i < cctvs.size(); i++) {
        int[] coord = cctvs.get(i);
        int r = coord[0];
        int c = coord[1];
        int cctvT = office[r][c] - 1;

        int[] dirs = dir[cctvT][directions[i]];

        for (int d : dirs) {
          if (d == 0) {
            for (int nr = r; nr >= 0; nr--) {
              if (office[nr][c] == 6) break;
              canMonitor[nr][c] = true;
            }
          }

          if (d == 1) {
            for (int nc = c; nc < C; nc++) {
              if (office[r][nc] == 6) break;
              canMonitor[r][nc] = true;
            }
          }

          if (d == 2) {
            for (int nr = r; nr < R; nr++) {
              if (office[nr][c] == 6) break;
              canMonitor[nr][c] = true;
            }
          }

          if (d == 3) {
            for (int nc = c; nc >= 0; nc--) {
              if (office[r][nc] == 6) break;
              canMonitor[r][nc] = true;
            }
          }
        }
      }

      int cnt = 0;
      for (int r = 0; r < R; r++) {
        for (int c = 0; c < C; c++) {
          if (office[r][c] == 6) canMonitor[r][c] = true;
          if (!canMonitor[r][c]) cnt++;
        }
      }

      answer = Math.min(answer, cnt);
      return;
    }

    int[] coord = cctvs.get(depth);
    int cctvT = office[coord[0]][coord[1]];

    int maxDir;
    switch (cctvT) {
      case 1:
      case 3:
      case 4: {
        maxDir = 4;
        break;
      }
      case 2: {
        maxDir = 2;
        break;
      }
      default: {
        maxDir = 1;
        break;
      }
    };

    for (int i = 0; i < maxDir; i++) {
      directions[depth] = i;
      solve(depth + 1, directions);
    }
  }
}