import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 1931 회의실 배정
  https://www.acmicpc.net/problem/1931
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.
    int N = Integer.parseInt(br.readLine());

    int[][] meetings = new int[N][2];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      meetings[i][0] = Integer.parseInt(st.nextToken());
      meetings[i][1] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(meetings, Comparator.<int[]>comparingInt(v -> v[1]).thenComparingInt(v -> v[0]));

    int prev = 0;
    int answer = 1;
    for (int i = 1; i < N; i++) {
      if (meetings[i][0] >= meetings[prev][1]) {
        prev = i;
        answer++;
      }
    }

    System.out.println(answer);
  }
}