import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2252 줄 세우기
  https://www.acmicpc.net/problem/2252
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    List<Integer>[] graph = new List[N + 1];
    for (int i = 1; i <= N; i++) {
      graph[i] = new ArrayList<>();
    }
    int[] indgree = new int[N + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph[a].add(b);
      indgree[b]++;
    }

    Deque<Integer> queue = new ArrayDeque<>();
    for (int i = 1; i <= N; i++) {
      if (indgree[i] == 0) {
        queue.addLast(i);
      }
    }

    while (!queue.isEmpty()) {
      int cur = queue.removeFirst();
      System.out.print(cur + " ");

      for (int next : graph[cur]) {
        indgree[next]--;
        if (indgree[next] == 0) {
          queue.addLast(next);
        }
      }
    }
  }
}