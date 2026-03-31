import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
  빠른 입출력 모드로 생성!
  BAEKJOON 2263 트리의 순회
  https://www.acmicpc.net/problem/2263
*/

public class Main {
    static int[] inIdx;
    static int[] in;
    static int[] post;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 코드를 작성하세요.

        int n = Integer.parseInt(br.readLine());

        in = new int[n];
        inIdx = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            in[i] = Integer.parseInt(st.nextToken());
            inIdx[in[i]] = i;
        }

        post = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }

        sb = new StringBuilder();
        solve(0, n - 1, 0, n - 1);

        System.out.println(sb);
    }

    static void solve(int inS, int inE, int postS, int postE) {
        if (inS > inE || postS > postE) return;

        int root = post[postE];

        int inRootIdx = inIdx[root];
        int leftSize = inRootIdx - inS;

        // root 출력
        sb.append(root).append(" ");

        //left 탐색
        solve(inS, inRootIdx - 1, postS, postS + leftSize - 1);

        // right 탐색
        solve(inRootIdx + 1, inE, postS + leftSize, postE - 1);
    }
}