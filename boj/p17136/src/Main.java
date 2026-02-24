import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17136 색종이 붙이기
  https://www.acmicpc.net/problem/17136
*/

public class Main {
  static int[][] paper;
  static int answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.
    paper = new int[10][10];

    for (int i = 0; i < 10; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 10; j++) {
        paper[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    answer = Integer.MAX_VALUE;
    solve(0, 0, 0, new int[] {0, 5, 5, 5, 5, 5});
    System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
  }

  static void solve(int r, int c, int cnt, int[] cps) {
    if (cnt >= answer) return;

    if (r >= 9 && c > 9) {
      answer = cnt;
      return;
    }

    if (c > 9) {
      solve(r + 1, 0, cnt, cps);
      return;
    }

    if (paper[r][c] == 1) {
      for (int size = 5; size >= 1; size--) {
        if (cps[size] == 0) continue;
        if (r + size > 10 || c + size > 10) continue;
        if (!check(r, c, size)) continue;

        toggle(r, c, size, 0);
        cps[size]--;
        solve(r, c + 1, cnt + 1, cps);
        cps[size]++;
        toggle(r, c, size, 1);
      }
    } else {
      solve(r, c + 1, cnt, cps);
    }

  }

  static void toggle(int sR, int sC, int size, int v) {
    for (int r = sR; r < sR + size; r++) {
      for (int c = sC; c < sC + size; c++) {
        paper[r][c] = v;
      }
    }
  }

  static boolean check(int sR, int sC, int size) {
    for (int r = sR; r < sR + size; r++) {
      for (int c = sC; c < sC + size; c++) {
        if (paper[r][c] != 1) return false;
      }
    }

    return true;
  }
}