import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 11727 2×n 타일링 2
  https://www.acmicpc.net/problem/11727
*/

public class Main {
  static int[] dp;
  static int MOD = 10007;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.

    int n = Integer.parseInt(br.readLine());
    if (n == 1) {
      System.out.println(1);
      return;
    }

    dp = new int[n + 1];
    dp[1] = 1;
    dp[2] = 3;

    System.out.println(solve(n));
  }

  static int solve(int n) {
    if (dp[n] == 0) {
      dp[n] = (solve(n - 1) + 2 * solve(n - 2)) % MOD;
    }

    return dp[n];
  }
}