import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2630 색종이 만들기
  https://www.acmicpc.net/problem/2630
*/

public class Main {
  static int N;
  static int[][] paper;
  static int white, blue;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.

    N = Integer.parseInt(br.readLine());
    paper = new int[N][N];
    for (int r = 0; r < N; r++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int c = 0; c < N; c++) {
        paper[r][c] = Integer.parseInt(st.nextToken());
      }
    }

    divide(0, 0, N, N);

    System.out.println(white);
    System.out.println(blue);
  }

  static void divide(int r1, int c1, int r2, int c2) {
    int color = paper[r1][c1];

    for (int r = r1; r < r2; r++) {
      for (int c = c1; c < c2; c++) {
        if (color != paper[r][c]) {
          divide(r1, c1, (r1 + r2) / 2, (c1 + c2) / 2);
          divide(r1, (c1 + c2) / 2, (r1 + r2) / 2 , c2);
          divide((r1 + r2) / 2, (c1 + c2) / 2, r2, c2);
          divide((r1 + r2) / 2, c1, r2, (c1 + c2) / 2);
          return;
        }
      }
    }

    if (color == 0) {
      white++;
    } else {
      blue++;
    }
  }
}