import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2567 색종이 - 2
  https://www.acmicpc.net/problem/2567
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.

    boolean[][] p = new boolean[101][101];
    int N = Integer.parseInt(br.readLine());
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int c1 = Integer.parseInt(st.nextToken());
      int r1 = Integer.parseInt(st.nextToken());

      for (int r = r1; r < r1 + 10; r++) {
        for (int c = c1; c < c1 + 10; c++) {
          p[r][c] = true;
        }
      }
    }

    int answer = 0;

    boolean prev = false;

    for (int r = 0; r < 101; r++) {
      for (int c = 0; c < 101; c++) {
        if (p[r][c] != prev) answer++;
        prev = p[r][c];
      }
    }

    for (int c = 0; c < 101; c++) {
      for (int r = 0; r < 101; r++) {
        if (p[r][c] != prev) answer++;
        prev = p[r][c];
      }
    }

    System.out.println(answer);
  }
}