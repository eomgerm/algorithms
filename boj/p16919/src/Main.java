import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 16919 봄버맨 2
  https://www.acmicpc.net/problem/16919
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int R = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    char[][] init = new char[R][C];
    for (int r = 0; r < R; r++) {
      init[r] =  br.readLine().toCharArray();
    }

    char[][] answer = {};

    char[][] full = new char[R][C];
    char[][] first = new char[R][C];
    char[][] second = new char[R][C];

    for (int r = 0; r < R; r++) {
      Arrays.fill(full[r], 'O');
      Arrays.fill(first[r], 'O');
      Arrays.fill(second[r], 'O');
    }

    int[] dr = new int[] {1, -1, 0 ,0};
    int[] dc = new int[] {0, 0, 1, -1};

    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (init[r][c] == 'O') {
          first[r][c] = '.';
          for (int i = 0; i < 4; i++) {
            int nR = r + dr[i];
            int nC = c + dc[i];

            if (nR < 0 || nR >= R || nC < 0 || nC >= C)
              continue;

            first[nR][nC] = '.';
          }
        }
      }
    }

    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (first[r][c] == 'O') {
          second[r][c] = '.';
          for (int i = 0; i < 4; i++) {
            int nR = r + dr[i];
            int nC = c + dc[i];

            if (nR < 0 || nR >= R || nC < 0 || nC >= C)
              continue;

            second[nR][nC] = '.';
          }
        }
      }
    }

    if (N == 1) {
      answer = init;
    } else if (N % 2 == 0) {
      answer = full;
    } else if (N % 4 == 3) {
      answer = first;
    } else if (N % 4 == 1) {
      answer = second;
    }

    for (char[] r : answer) {
      bw.write(new String(r) + "\n");
    }
    bw.flush();
    bw.close();
  }
}