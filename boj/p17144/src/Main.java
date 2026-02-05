import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17144 미세먼지 안녕!
  https://www.acmicpc.net/problem/17144
*/

public class Main {
  static int R;
  static int C;
  static int T;
  static int[][] room;
  static int purifier;


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    T = Integer.parseInt(st.nextToken());

    room = new int[R][C];
    for (int r = 0; r < R; r++) {
      st = new StringTokenizer(br.readLine());
      for (int c = 0; c < C; c++) {
        room[r][c] = Integer.parseInt(st.nextToken());
        if (room[r][c] == -1 && purifier == 0) {
          purifier = r;
        }
      }
    }

    while (T --> 0) {
      diffuse();
      purify();
    }

    int answer = 0;
    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        answer += room[r][c];
      }
    }
    answer += 2;
    bw.write(answer + "");
    bw.close();
  }

  static void diffuse() {
    List<int[]> diffused = new ArrayList<>();
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};

    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (room[r][c] == -1) continue;

        int amt = room[r][c] / 5;
        for (int i = 0; i < 4; i++) {
          int nR = r + dr[i];
          int nC = c + dc[i];

          if (nR < 0 || nR >= R || nC < 0 || nC >= C) continue;
          if (room[nR][nC] == -1) continue;

          diffused.add(new int[] {nR, nC, amt});
          room[r][c] -= amt;
        }
      }
    }

    for (int[] d : diffused) {
      int r = d[0];
      int c = d[1];
      int amt = d[2];

      room[r][c] += amt;
    }
  }

  static void purify() {
    int top = 0, left = 0, bottom = purifier, right = C - 1;

    for (int i = bottom - 1; i >= top; i--) {
      room[i + 1][left] = room[i][left];
    }

    for (int i = left + 1; i <= right; i++) {
      room[top][i - 1] = room[top][i];
    }

    for (int i = top + 1; i <= bottom; i++) {
      room[i - 1][right] = room[i][right];
    }

    for (int i = right - 1; i >= left; i--) {
      room[bottom][i + 1] = room[bottom][i];
    }

    room[purifier][1] = 0;
    room[purifier][0] = -1;

    top = purifier + 1;
    bottom = R - 1;

    for (int i = top + 1; i <= bottom; i++) {
      room[i - 1][left] = room[i][left];
    }

    for (int i = left + 1; i <= right; i++) {
      room[bottom][i - 1] = room[bottom][i];
    }

    for (int i = bottom - 1; i >= top; i--) {
      room[i + 1][right] = room[i][right];
    }

    for (int i = right - 1; i >= left; i--) {
      room[top][i + 1] = room[top][i];
    }

    room[purifier + 1][1] = 0;
    room[purifier + 1][0] = -1;
  }
}