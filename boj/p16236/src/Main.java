import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 16236 아기 상어
  https://www.acmicpc.net/problem/16236
*/

public class Main {
  static int N;
  static int[][] map;
  static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.
    N = Integer.parseInt(br.readLine());

    map = new int[N][N];
    int[] shark = {};
    for (int r = 0; r < N; r++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int c = 0; c < N; c++) {
        int v = Integer.parseInt(st.nextToken());
        map[r][c] = v;
        if (v == 9) {
          shark = new int[] {r, c};
        }
      }
    }

    int answer = 0;
    int sharkSize = 2;
    int fishCnt = sharkSize;

    while (true) {
      int[] fish = find(shark, sharkSize);
      if (fish == null) break;

      map[shark[0]][shark[1]] = 0;
      shark = new int[] {fish[0], fish[1]};
      map[shark[0]][shark[1]] = 9;
      if (--fishCnt == 0) {
        fishCnt = ++sharkSize;
      }
      answer += fish[2];
    }

    System.out.println(answer);
  }

  static int[] find(int[] shark, int sharkSize) {
    Deque<int[]> queue = new ArrayDeque<>();
    boolean[][] visited = new boolean[N][N];
    List<int[]> cand =  new ArrayList<>();
    int minD = Integer.MAX_VALUE;

    queue.addLast(new int[] {shark[0], shark[1], 0});
    visited[shark[0]][shark[1]] = true;
    while (!queue.isEmpty()) {
      int[] cur = queue.removeFirst();
      int cR = cur[0];
      int cC = cur[1];
      int d = cur[2];

      if (d > minD) continue;

      if (map[cR][cC] > 0 && map[cR][cC] < sharkSize && map[cR][cC] != 9) {
        minD = d;
        cand.add(cur);
      }

      for (int i = 0; i < 4; i++) {
        int nR = cR + dr[i];
        int nC = cC + dc[i];

        if (nR < 0 || nR >= N || nC < 0 || nC >= N) continue;
        if (visited[nR][nC]) continue;
        if (map[nR][nC] > sharkSize) continue;

        visited[nR][nC] = true;
        queue.addLast(new int[]{nR, nC, d + 1});
      }
    }

    cand.sort(Comparator.<int[]>comparingInt(c -> c[0]).thenComparingInt(c -> c[1]));

    return cand.isEmpty() ? null : cand.get(0);
  }
}