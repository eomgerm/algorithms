import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17472 다리 만들기 2
  https://www.acmicpc.net/problem/17472
*/

public class Main {
  static int N, M;
  static int[][] map;
  static boolean[][] visited;
  static int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
  static List<int[]>[] graph;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    for (int r = 0; r < N; r++) {
      st = new StringTokenizer(br.readLine());
      for (int c = 0; c < M; c++) {
        map[r][c] = Integer.parseInt(st.nextToken());
      }
    }

    // 1. 섬 구분하기
    visited = new boolean[N][M];
    int islandCount = 0;
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < M; c++) {
        if (map[r][c] != 0 && !visited[r][c]) {
          island(r, c, ++islandCount);
        }
      }
    }

    // 2. 그래프 만들기
    graph = new List[islandCount + 1];
    for (int i = 0; i <= islandCount; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < M; c++) {
        if (map[r][c] == 0) continue;

        int from = map[r][c];
        for (int i = 0; i < 4; i++) {
          int nR = r + dr[i];
          int nC = c + dc[i];
          int w = 0;

          while (nR >= 0 && nR < N && nC >= 0 && nC < M) {
            if (map[nR][nC] == from) break;

            if (map[nR][nC] == 0) {
              w++;
            } else {
              if (w >= 2) {
                int to = map[nR][nC];

                graph[from].add(new int[] {to, w});
              }
              break;
            }

            nR += dr[i];
            nC += dc[i];
          }
        }
      }
    }

    // 3. MST 구하기
    boolean[] visited = new boolean[islandCount + 1];

    int mst = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
    pq.add(new int[] {1, 0});

    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      int node = cur[0];
      int w = cur[1];

      if (visited[node]) continue;
      visited[node] = true;
      mst += w;
      for (int[] next : graph[node]) {
        int nN = next[0];
        int nW = next[1];

        if (!visited[nN]) {

          pq.add(new int[] {nN, nW});
        }
      }
    }

    for (int i = 1; i <= islandCount; i++) {
      if (!visited[i]) {
        System.out.println(-1);
        return;
      }
    }
    System.out.println(mst);
  }

  static void island(int sR, int sC, int idx) {
    Deque<int[]> queue = new ArrayDeque<>();

    queue.addLast(new int[] {sR, sC});
    visited[sR][sC] = true;

    while (!queue.isEmpty()) {
      int[] cur = queue.removeFirst();
      int cR = cur[0];
      int cC = cur[1];

      map[cR][cC] = idx;

      for (int i = 0; i < 4; i++) {
        int nR = cR + dr[i];
        int nC = cC + dc[i];

        if (nR < 0 || nR >= N || nC < 0 || nC >= M) continue;
        if (map[nR][nC] == 0 || visited[nR][nC]) continue;

        visited[nR][nC] = true;
        queue.addLast(new int[] {nR, nC});
      }
    }
  }
}