import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17406 배열 돌리기 4
  https://www.acmicpc.net/problem/17406
*/

public class Main {
  static int R, C;
  static int[][] A;
  static int K;
  static int[] result;
  static boolean[] visited;
  static int[][] op;
  static int answer = Integer.MAX_VALUE;


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    A = new int[R+1][C + 1];
    for (int r = 1; r <= R; r++) {
      st = new StringTokenizer(br.readLine());
      for (int c = 1; c <= C; c++) {
        A[r][c] = Integer.parseInt(st.nextToken());
      }
    }

    op = new int[K][3];
    for (int k = 0; k < K; k++) {
      st = new StringTokenizer(br.readLine());
      op[k][0] = Integer.parseInt(st.nextToken());
      op[k][1] = Integer.parseInt(st.nextToken());
      op[k][2] = Integer.parseInt(st.nextToken());
    }

    visited = new boolean[K];
    result = new int[K];
    perm(0);

    System.out.println(answer);
  }

  static void rotate(int[][] A, int ri, int ci, int s) {
    int top = ri - s, bottom = ri + s, right = ci + s, left = ci - s;

    int t = A[top][left];
    for (int r = top + 1; r <= bottom; r++) {
      A[r - 1][left] = A[r][left];
    }

    for (int c = left + 1; c <= right; c++) {
      A[bottom][c - 1] = A[bottom][c];
    }

    for (int r = bottom - 1; r >= top; r--) {
      A[r + 1][right] = A[r][right];
    }

    for (int c = right - 1; c >= left; c--) {
      A[top][c + 1] = A[top][c];
    }

    A[top][left + 1] = t;
  }

  static void rotateSquare(int[][] A,int r, int c, int s) {
    for (int i = s; i > 0; i--) {
      rotate(A, r, c, i);
    }
  }

  static void perm(int cnt) {
    if (cnt == K) {
      int[][] m = new int[R + 1][C  + 1];
      for (int r = 1; r <= R; r++) {
        m[r] = A[r].clone();
      }

      for (int i : result) {
        rotateSquare(m, op[i][0], op[i][1], op[i][2]);
      }

      for (int r = 1; r < R + 1; r++) {
        answer = Math.min(answer, Arrays.stream(m[r]).sum());
      }

      return;
    }

    for (int i = 0; i < K; i++) {
      if (visited[i]) continue;
      visited[i] = true;
      result[cnt] = i;
      perm(cnt + 1);
      visited[i] = false;
    }
  }
}