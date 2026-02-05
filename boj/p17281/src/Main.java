import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17281 ⚾
  https://www.acmicpc.net/problem/17281
*/

public class Main {
  static int answer = 0;
  static int[][] players;
  static int N;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.
    N = Integer.parseInt(br.readLine());
    players = new int[N][9];

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 9; j++) {
        players[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    perm(0, new boolean[9], new int[9]);

    bw.write(answer + "");
    bw.flush();
    bw.close();
  }

  static void perm(int depth, boolean[] visited, int[] result) {
    if (depth == 3) {
      result[depth] = 0;
      perm(depth + 1, visited, result);
      return;
    }

    if (depth == 9) {
      answer = Math.max(answer, score(result));
      return;
    }

    for (int i = 1; i < 9; i++) {
      if (!visited[i]) {
        visited[i] = true;
        result[depth] = i;
        perm(depth + 1, visited, result);
        visited[i] = false;
      }
    }

  }

  static int score(int[] order) {
    int batterIdx = 0;
    int bases = 0;

    int out = 0;
    int inning = 0;
    int score = 0;

    while (inning < N) {
      int batter = order[(batterIdx++) % 9];
      int result = players[inning][batter];

      if (result == 0) {
        out++;
      } else {
        bases = (bases << result) | (1 << (result - 1));
        score += Integer.bitCount(bases >> 3);
        bases &= 0b111;
      }

      if (out == 3) {
        out = 0;
        inning++;
        bases = 0;
      }
    }

    return score;
  }
}