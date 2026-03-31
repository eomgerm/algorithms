import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 22856 트리 순회
  https://www.acmicpc.net/problem/22856
*/

public class Main {
    static int[] right;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.

        int N = Integer.parseInt(br.readLine());

        right = new int[N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            st.nextToken();

            int c = Integer.parseInt(st.nextToken());
            if (c != -1) {
                right[a] = c;
            }
        }

        int answer = 2 * (N - 1);

        int cur = 1;
        while (right[cur] != 0) {
            answer--;
            cur = right[cur];
        }

        System.out.println(answer);
    }
}