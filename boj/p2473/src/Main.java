import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2473 세 용액
  https://www.acmicpc.net/problem/2473
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.
    int N = Integer.parseInt(br.readLine());
    long[] sols = new long[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      sols[i] = Long.parseLong(st.nextToken());
    }

    Arrays.sort(sols);

    int al = 0, ar = 0, ac = 0;

    long min = Long.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      int l = i + 1, r = N - 1;
      while (l < r) {
        long sum = sols[i]  + sols[l] + sols[r];
        if (Math.abs(sum) < min) {
          min = Math.abs(sum);
          al = i;
          ac = l;
          ar = r;
        }

        if (sum < 0) l++;
        else if (sum > 0) r--;
        else break;
      }

      if (min == 0) break;
    }

    System.out.println(sols[al] + " " + sols[ac] + " " + sols[ar]);
  }
}