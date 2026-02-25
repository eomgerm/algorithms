import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 15684 사다리 조작
  https://www.acmicpc.net/problem/15684
*/

public class Main {
  static int N, H;
  static boolean[][] ladder;
  static boolean found;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    H =  Integer.parseInt(st.nextToken());

    ladder = new boolean[H + 1][N + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      ladder[a][b] = true;
    }

    for (int i = 0; i <= 3 ; i++) {
      solve(1, 1, 0, i);
      if (found) {
        System.out.println(i);
        return;
      }
    }
    System.out.println(-1);
  }

  static void solve(int r, int c, int cnt, int l) {
    if (found) {
      return;
    }

    if (cnt == l) {
      boolean ok = true;
      for (int i = 1; i <= N; i++) {
        int cur = i;
        for (int j = 1; j <= H; j++) {
          if (ladder[j][cur - 1]) cur--;
          else if (cur <= N && ladder[j][cur]) cur++;
        }
        if (cur != i) {
          ok = false;
          break;
        }
      }

      if (ok) {
        found = true;
        return;
      }
    }

    for (int i = r; i <= H; i++) {
      int sC = (i == r) ? c : 1;
      for (int j = sC; j < N; j++) {
        if (!ladder[i][j] && !ladder[i][j - 1] && !ladder[i][j + 1]) {
          ladder[i][j] = true;
          solve(i, j + 2, cnt + 1, l);
          ladder[i][j] = false;
        }
      }
    }
  }
}