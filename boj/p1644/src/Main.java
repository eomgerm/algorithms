import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 1644 소수의 연속합
  https://www.acmicpc.net/problem/1644
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.

        int N = Integer.parseInt(br.readLine());

        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i < Math.sqrt(N) + 1; i++) {
            if (!isPrime[i]) continue;

            for (int j = 2 * i; j <= N; j += i) {
                isPrime[j] = false;
            }
        }

        int l = 2, r = 2;
        int answer = 0;

        int sum = 2;
        while (l <= r) {
            if (sum < N) {
                r++;
                while (r <= N && !isPrime[r]) {
                    r++;
                }
                if (l != r) {
                    sum += r;
                }
            } else if (sum > N) {
                if (l != r) {
                    sum -= l;
                }
                l++;
                while (l <= N && !isPrime[l]) {
                    l++;
                }
            } else {
                answer++;
                if (l == N) break;

                if (l != r) {
                    sum -= l;
                }
                l++;
                while (!isPrime[l]) {
                    l++;
                }
            }
        }

        System.out.println(answer);
    }
}