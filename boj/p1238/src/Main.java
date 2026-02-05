import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 1238 파티
  https://www.acmicpc.net/problem/1238
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 코드를 작성하세요.

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int x = Integer.parseInt(st.nextToken());

    int[][] roads = new int[m][3];
    for (int i = 0 ; i < m; i++) {
      st = new StringTokenizer(br.readLine());

      roads[i][0] = Integer.parseInt(st.nextToken());
      roads[i][1] = Integer.parseInt(st.nextToken());
      roads[i][2] = Integer.parseInt(st.nextToken());
    }

    bw.write(new Solution().solution(n, x, roads) + "");
    bw.flush();
    bw.close();
  }
}

class Solution {
  int solution(int n, int x, int[][] roads) {
    int answer = 0;

    List<int[]>[] graph = new ArrayList[n + 1];
    for (int i = 1; i < n+1; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int[] road : roads) {
      int s = road[0];
      int e = road[1];
      int t = road[2];

      graph[s].add(new int[] {e, t});
    }

    int[] returnD = new int[n+1];
    Arrays.fill(returnD, Integer.MAX_VALUE);
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));

    returnD[x] = 0;
    pq.add(new int[] {x, 0});

    while(!pq.isEmpty()) {
      int[] cur = pq.poll();
      int curV = cur[0];
      int curD = cur[1];

      for (int[] next : graph[curV]) {
        int nextV = next[0];
        int nextD = next[1];
        if (returnD[nextV] > curD + nextD) {
          pq.add(new int[] {nextV, curD + nextD});
          returnD[nextV] = curD + nextD;
        }
      }
    }

    for (int start = 1; start < n+1; start++) {
      int[] goD = new int[n+1];
      Arrays.fill(goD, Integer.MAX_VALUE);

      goD[start] = 0;
      pq.add(new int[] {start, 0});

      while(!pq.isEmpty()) {
        int[] cur = pq.poll();
        int curV = cur[0];
        int curD = cur[1];

        for (int[] next : graph[curV]) {
          int nextV = next[0];
          int nextD = next[1];
          if (goD[nextV] > curD + nextD) {
            pq.add(new int[] {nextV, curD + nextD});
            goD[nextV] = curD + nextD;
          }
        }
      }

      answer = Math.max(answer, goD[x] + returnD[start]);
    }

    return answer;
  }
}