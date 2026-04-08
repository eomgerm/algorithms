import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 13144 List of Unique Numbers
  https://www.acmicpc.net/problem/13144
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.

        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int l = 0;
        int r = 0;
        boolean[] a = new boolean[100001];
        while (r < N) {
            while (r < N) {
                if (a[seq[r]]) break;
                a[seq[r]] = true;
                r++;
            }
            int len = r - l;
            answer += len * (len + 1) / 2;

            while (l < r && r < N) {
                a[seq[l]] = false;
                l++;
                if (!a[seq[r]]) break;
            }
        }

        System.out.println(answer);
    }
}