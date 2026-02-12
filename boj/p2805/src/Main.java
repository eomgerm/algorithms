import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2805 나무 자르기
  https://www.acmicpc.net/problem/2805
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    long M = Long.parseLong(st.nextToken());

    st = new StringTokenizer(br.readLine());
    long[] trees = new long[N];
    long max = 0;
    for (int i = 0; i < N; i++) {
      trees[i] = Integer.parseInt(st.nextToken());
      max = Math.max(max, trees[i]);
    }

    long answer = 0;
    long s = 1, e = max;
    while (s <= e) {
      long mid = (s + e) / 2;

      long m = 0;
      for (int i = 0; i < N; i++) {
        m += trees[i] - mid >= 0 ? trees[i] - mid : 0;
      }

      if (m < M) {
        e = mid - 1;
      } else {
        answer = mid;
        s = mid + 1;
      }
    }

    System.out.println(answer);
  }
}