import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 3109 빵집
  https://www.acmicpc.net/problem/3109
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.

    StringTokenizer st = new StringTokenizer(br.readLine());
    int R = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());

    char[][] map = new char[R][C];
    for (int r = 0; r < R; r++) {
      map[r] = br.readLine().toCharArray();
    }

    ArrayDeque<int[]> stack = new ArrayDeque<>();
    boolean[][] visited = new boolean[R][C];

    int answer = 0;
    int[] dr = {1, 0, -1};
    for (int r = 0; r < R; r++) {
      stack.add(new int[] {r, 0});
      visited[r][0] = true;

      while (!stack.isEmpty()) {
        int[] cur = stack.removeFirst();
        int curR = cur[0];
        int curC = cur[1];

        if (curC == C - 1) {
          answer++;
          while (!stack.isEmpty()) {
            int[] c = stack.removeFirst();
            visited[c[0]][c[1]] = false;
          }
          break;
        }

        for (int i = 0; i < 3; i++) {
          int nextR = curR + dr[i];
          int nextC = curC + 1;

          if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C) continue;
          if (visited[nextR][nextC]) continue;
          if (map[nextR][nextC] == 'x') continue;
          stack.addFirst(new int[] {nextR, nextC});
          visited[nextR][nextC] = true;
        }
      }
    }

    System.out.println(answer);
  }
}