import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2042 구간 합 구하기
  https://www.acmicpc.net/problem/2042
*/

public class MainSegment {
  static long[] A;
  static long[] tree;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    // 코드를 작성하세요.
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    A = new long[N + 1];
    for (int i = 1; i <= N; i++) {
      A[i] = Long.parseLong(br.readLine());
    }

    tree = new long[4 * N];
    init(1, N, 1);

    for (int i = 0; i < M + K ; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (a == 1) {
        long c = Long.parseLong(st.nextToken());
        update(1, N, 1, b, c - A[b]);
        A[b] = c;
      } else {
        int c = Integer.parseInt(st.nextToken());
        sb.append(query(1, N, b, c, 1)).append("\n");
      }
    }

    System.out.println(sb);
  }

  static void init(int start, int end, int idx) {
    if (start == end) {
      tree[idx] = A[start];
      return;
    }

    int mid = (start + end) / 2;
    init(start, mid, idx * 2);
    init(mid + 1, end, idx * 2 + 1);
    tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
  }

  static long query(int start, int end, int left, int right, int idx) {
    if (left > end || right < start) {
      return 0;
    }

    if (left <= start && right >= end) {
      return tree[idx];
    }

    int mid = (start + end) / 2;
    long lSum = query(start, mid, left, right, idx * 2);
    long rSum = query(mid + 1, end, left, right, idx * 2 + 1);

    return lSum + rSum;
  }

  static void update(int start, int end, int idx, int targetIdx, long diff) {
    if (targetIdx < start || targetIdx > end) return;
    tree[idx] += diff;
    if (start != end) {
      int mid = (start + end) / 2;
      update(start, mid, idx * 2, targetIdx, diff);
      update(mid + 1, end, idx * 2 + 1, targetIdx, diff);
    }
  }
}