import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 17471 게리맨더링
  https://www.acmicpc.net/problem/17471
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.

    int N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] p = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      p[i] = Integer.parseInt(st.nextToken());
    }

    List<Integer>[] graph = new List[N + 1];
    for (int i = 1; i <= N; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      int M = Integer.parseInt(st.nextToken());

      for (int j = 0; j < M; j++) {
        int b = Integer.parseInt(st.nextToken());
        graph[i].add(b);
      }
    }

    int answer = Integer.MAX_VALUE;
    for (int i = 1; i < (1 << N) - 1; i++) {
      int c = i << 1;

      Set<Integer> a = new HashSet<>();
      Set<Integer> b = new HashSet<>();

      int aS = 0, bS = 0;

      for (int j = 1; j <= N; j++) {
        if ((c & (1 << j)) == 0) {
          aS = j;
          a.add(j);
        } else {
          bS = j;
          b.add(j);
        }
      }

      int aSum = 0, bSum = 0;
      Deque<Integer> queue = new ArrayDeque<>();

      queue.addLast(aS);
      a.remove(aS);

      while (!queue.isEmpty()) {
        int cur = queue.removeFirst();
        aSum += p[cur];

        for (int n : graph[cur]) {
          if (a.contains(n)) {
            queue.add(n);
            a.remove(n);
          }
        }
      }

      if (!a.isEmpty()) continue;

      queue.addLast(bS);
      b.remove(bS);

      while (!queue.isEmpty()) {
        int cur = queue.removeFirst();
        bSum += p[cur];

        for (int n : graph[cur]) {
          if (b.contains(n)) {
            queue.add(n);
            b.remove(n);
          }
        }
      }

      if (!b.isEmpty()) continue;

      answer = Math.min(answer, Math.abs(aSum - bSum));
    }

    System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
  }
}