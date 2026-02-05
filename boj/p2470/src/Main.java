import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2470 두 용액
  https://www.acmicpc.net/problem/2470
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    long[] c = new long[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      c[i] = Long.parseLong(st.nextToken());
    }

    Arrays.sort(c);

    int al = 0;
    int ar = 0;
    int l = 0;
    int r = N - 1;
    long min = Long.MAX_VALUE;
    long sum = 0;
    while (l < r) {
      sum = c[l] + c[r];
      if (Math.abs(sum) <= min) {
        al = l;
        ar = r;
        min = Math.abs(sum);
      }
      if (sum < 0) l++;
      else if (sum > 0) r--;
      else break;
    }

    System.out.println(c[al] + " " + c[ar]);
  }
}