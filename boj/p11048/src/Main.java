import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 11048 이동하기
  https://www.acmicpc.net/problem/11048
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] dp = new int[N + 1][M + 1];
    for (int r = 1; r <= N; r++) {
      st = new StringTokenizer(br.readLine());
      for (int c = 1; c <= M; c++) {
        dp[r][c] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= M; j++) {
        dp[i][j] += Math.max(dp[i - 1][j], Math.max(dp[i][j - 1], dp[i - 1][j - 1]));
      }
    }

    System.out.println(dp[N][M]);
  }
}