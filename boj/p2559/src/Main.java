import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2559 수열
  https://www.acmicpc.net/problem/2559
*/

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 코드를 작성하세요.

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[] t = new int[N];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      t[i] = Integer.parseInt(st.nextToken());
    }

    // 초기 합 구하기
    int sum = 0;
    for (int i = 0; i < K; i++) {
      sum += t[i];
    }
    int answer = sum;

    // 투포인터
    int l = 0; // 구간 첫번째 인덱스
    int r = K - 1; // 구간 마지막 인덱스
    while (r < N - 1) {
      r++;
      sum = sum - t[l] + t[r]; // 첫번째 걸 빼고 다음 구간 마지막을 더하기
      l++;

      answer = Math.max(answer, sum);
    }

    System.out.println(answer);
  }
}